package org.example.Business;

import org.example.Model.ClasamentDTO;
import org.example.Model.ConcurentDTO;
import org.example.Model.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class DataService {

    private final BlockingQueue<DataDTO> dataQueue = new LinkedBlockingQueue<>();
    private final ExecutorService readerThreadPool;
    private final ExecutorService writerThreadPool;
    private final List<ConcurentDTO> globalList = Collections.synchronizedList(new ArrayList<>());

    private boolean finished = false;

    @Autowired
    public DataService(@Value("${threads.read}") int p_r, @Value("${threads.write}") int p_w) {
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
        synchronized (globalList) {
            globalList.add(new ConcurentDTO(data.getTara(), data.getIdConcurent(), data.getPunctaj()));
        }
    }

    public List<ConcurentDTO> getGlobalList() {
        return globalList;
    }

    public void finish(){
        finished = true;
    }
}
