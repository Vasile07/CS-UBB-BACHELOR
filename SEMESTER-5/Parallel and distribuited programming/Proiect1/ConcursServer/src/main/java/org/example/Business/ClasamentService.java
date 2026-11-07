package org.example.Business;

import org.example.Model.ClasamentDTO;
import org.example.Model.ClasamentFinalDTO;
import org.example.Model.ConcurentDTO;
import org.example.Model.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ClasamentService {
    private List<ClasamentDTO> cachedClasament = new ArrayList<>();
    private long lastUpdated = 0;
    private final ReentrantLock lock = new ReentrantLock();

    @Value("${delta.t}")
    private long deltaT;

    @Value("${countries}")
    private long numberOfCountries;
    private final BlockingQueue<DataDTO> dataQueue = new LinkedBlockingQueue<>();
    private final ExecutorService readerThreadPool;
    private final ExecutorService writerThreadPool;
    private final List<ConcurentDTO> globalList = Collections.synchronizedList(new ArrayList<>());
    private boolean finished = false;

    private final Set<String> finishedCountries = new HashSet<>();
    private final Object lockForFinished = new Object();  // Used to control thread waiting

    @Autowired
    public ClasamentService(@Value("${threads.read}") int p_r, @Value("${threads.write}") int p_w) {
        this.readerThreadPool = Executors.newFixedThreadPool(p_r);
        this.writerThreadPool = Executors.newFixedThreadPool(p_w);
        startProcessing();
    }

    public void addDataToQueue(List<DataDTO> data) {
        dataQueue.addAll(data);
    }

    private void startProcessing() {
        for (int i = 0; i < 5; i++) {
            readerThreadPool.submit(this::processQueue);
        }
    }

    private void processQueue() {
        try {
            while (!finished) {
                DataDTO data = dataQueue.take();
                writerThreadPool.submit(() -> updateGlobalList(data));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void updateGlobalList(DataDTO data) {
        synchronized (this) {
            globalList.add(new ConcurentDTO(data.getTara(), data.getIdConcurent(), data.getPunctaj()));
            verifyFinished(data.getTara());
        }
    }

    public CompletableFuture<List<ClasamentDTO>> getPartialClasamentAsync(String tara) {
        finishedCountries.add(tara);
        return CompletableFuture.supplyAsync(() -> {
            long now = System.currentTimeMillis();
            if (now - lastUpdated > deltaT) {
                lock.lock();
                try {
                    if (now - lastUpdated > deltaT) {
                        cachedClasament = calculateClasament();
                        lastUpdated = now;
                    }
                } finally {
                    lock.unlock();
                }
            }
            return cachedClasament;
        });
    }

    private List<ClasamentDTO> calculateClasament() {
        Map<String, Integer> scoresByCountry = new HashMap<>();
        for (ConcurentDTO concurent : globalList) {
            scoresByCountry.merge(concurent.getTara(), concurent.getPunctaj(), Integer::sum);
        }
        return scoresByCountry.entrySet().stream()
                .map(entry -> new ClasamentDTO(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(ClasamentDTO::getPunctajFinal).reversed())
                .toList();
    }

    private void verifyFinished(String tara) {
        finishedCountries.add(tara);

        finished = finishedCountries.size() == numberOfCountries && dataQueue.isEmpty();

        if (finished) {
            synchronized (lockForFinished) {
                lockForFinished.notifyAll();  // Notify all waiting threads
            }
        }
    }

    public ClasamentFinalDTO getClasamentFinal() throws InterruptedException {
        synchronized (lockForFinished) {
            while (!finished) {
                lockForFinished.wait();
            }
        }

        Map<String, Integer> scoresByCountry = new HashMap<>();
        Map<String, Integer> scoresByConcurent = new HashMap<>();

        synchronized (globalList) {
            for (ConcurentDTO concurent : globalList) {
                scoresByCountry.merge(concurent.getTara(), concurent.getPunctaj(), Integer::sum);
                scoresByConcurent.merge(concurent.getIdConcurent().toString(), concurent.getPunctaj(), Integer::sum);
            }
        }
        List<ClasamentDTO> clasamentTari = scoresByCountry.entrySet().stream()
                .map(entry -> new ClasamentDTO(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(ClasamentDTO::getPunctajFinal).reversed())
                .toList();

        List<ClasamentDTO> clasamentConcurenti = scoresByConcurent.entrySet().stream()
                .map(entry -> new ClasamentDTO(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(ClasamentDTO::getPunctajFinal).reversed())
                .toList();

        return new ClasamentFinalDTO(clasamentTari, clasamentConcurenti);
    }
}
