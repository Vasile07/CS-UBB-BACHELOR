#include<iostream>
#include <thread>
#include <vector>


using namespace std;

int MAX = 10000000;

void suma_secvential(double *v1, double *v2, double *v3, int start, int end) {
    for (int i = start; i < end; i++) {
        v3[i] = v1[i] + v2[i];
    }
}

void suma_paralela(double *v1, double *v2, double *v3, int size, int nr_threads) {
    thread *threads = new thread[nr_threads];
    int start = 0;
    int workload = size / nr_threads;
    int rest = size % nr_threads;

    for (int i = 0; i < nr_threads; i++) {
        int end = start + workload;
        if (rest > 0) {
            end++;
            rest--;
        }
        threads[i] = thread(&suma_secvential, v1, v2, v3, start, end);
        start = end;
    }
    for (int i = 0; i < nr_threads; i++)
        threads[i].join();
}


void suma_ciclica(double *v1, double *v2, double *v3, int size, int nr_thread) {
    vector<thread *> threads = vector<thread *>();
    for (int i = 0; i < nr_thread; i++) {
        thread *t = new thread([nr_thread](double *v1, double *v2, double *v3, int start, int end) {
            for (int i = start; i < end; i += nr_thread) {
                v3[i] = v1[i] + v2[i];
            }
        }, v1, v2, v3, i, size);
        threads.push_back(t);
    }

    for (auto thread: threads) {
        thread->join();
        delete thread;
    }
}

int main() {

    int nr_threads = 3;
    double *a = new double[MAX];
    double *b = new double[MAX];
    double *cS = new double[MAX];
    double *cP = new double[MAX];
    double *cC = new double[MAX];

    for (int i = 0; i < MAX; i++) {
        a[i] = i;
        b[i] = 2 * i;
    }

    auto startS = chrono::high_resolution_clock::now();
    suma_secvential(a, b, cS, 0, MAX);
    auto endS = chrono::high_resolution_clock::now();
    auto durationS = chrono::duration_cast<chrono::microseconds>(endS - startS);
    cout << "Secvential: " << durationS.count() << endl;

    auto startP = chrono::high_resolution_clock::now();
    suma_paralela(a, b, cP, MAX, nr_threads);
    auto endP = chrono::high_resolution_clock::now();
    auto durationP = chrono::duration_cast<chrono::microseconds>(endP - startP);
    cout << "Paralel: " << durationP.count() << endl;

    auto startC = chrono::high_resolution_clock::now();
    suma_ciclica(a, b, cC, MAX, nr_threads);
    auto endC = chrono::high_resolution_clock::now();
    auto durationC = chrono::duration_cast<chrono::microseconds>(endC - startC);
    cout << "Ciclic: " << durationC.count() << endl;

    delete[] a;
    delete[] b;
    delete[] cS;
    delete[] cP;
    return 0;
}
