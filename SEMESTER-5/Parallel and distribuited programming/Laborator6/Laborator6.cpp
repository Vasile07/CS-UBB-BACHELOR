#include "iostream"
#include "mpi.h"
#include "stdio.h"
#include "string.h"

using namespace std;
#define MAX_SIZE 100
#define MAX_PROCESS 10

//int hello(const int argc, char** argv) {
//	int id, nrprocs;
//	MPI_Init(&argc, &argv);
//	MPI_Comm_size(MPI_COMM_WORLD, &nrprocs);
//	MPI_Comm_rank(MPI_COMM_WORLD, &id);
//
//	char message[MAX_SIZE];
//	snprintf(message, MAX_SIZE, "Hello from process %d", id);
//
//	if (id != 0) {
//		MPI_Send(message, strlen(message) + 1, MPI_CHAR, 0, 0, MPI_COMM_WORLD);
//	}
//	else {
//		char final_message[MAX_SIZE * MAX_PROCESS];
//		strcpy_s(final_message, message);
//		for (int i = 1; i < nrprocs; i++) {
//			MPI_Recv(final_message + strlen(final_message), MAX_SIZE, MPI_CHAR, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
//		}
//		cout << "Process [0] message: \n" << final_message << "\n";
//	}
//
//	MPI_Finalize();
//	return 0;
//}

void print(int* a, int n) {
	for (int i = 0; i < n; i++)
		cout << a[i] << " ";

	cout << "\n";
}

const int N = 10;

int main(int argc, char** argv) {
	int id, nrprocs;
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &nrprocs);
	MPI_Comm_rank(MPI_COMM_WORLD, &id);

	int a[N];
	int b[N];
	int c[N];

	if (id == 0) {
		for (int i = 0; i < N; i++) {
			a[i] = rand() % 10;
			b[i] = rand() % 10;
		}
	}

	int sizeChunk = N / nrprocs;
	const int reminder = N % nrprocs;

	int* auxA = new int[sizeChunk];
	int* auxB = new int[sizeChunk];
	int* auxC = new int[sizeChunk];

	int* sendCounts = new int[nrprocs];
	int* displacement = new int[nrprocs];
	int offset = 0;
	for (int i = 0; i < nrprocs; i++)
	{
		if (i < reminder)
		{
			sendCounts[i] = sizeChunk + 1;
		}
		else
		{
			sendCounts[i] = sizeChunk;
		}
		displacement[i] = offset;
		offset += sendCounts[i];
	}


	int recvSize = sendCounts[id];

	//MPI_Scatter(a, sizeChunk, MPI_INT, auxA, sizeChunk, MPI_INT, 0, MPI_COMM_WORLD);
	//MPI_Scatter(b, sizeChunk, MPI_INT, auxB, sizeChunk, MPI_INT, 0, MPI_COMM_WORLD);

	MPI_Scatterv(a, sendCounts, displacement, MPI_INT, auxA, recvSize, MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Scatterv(b, sendCounts, displacement, MPI_INT, auxB, recvSize, MPI_INT, 0, MPI_COMM_WORLD);

	for (int i = 0; i < recvSize; i++) {
		auxC[i] = auxA[i] + auxB[i];
	}

	//MPI_Gather(auxC, sendCounts[id], MPI_INT, c, sendCounts[id], MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Gatherv(auxC, recvSize, MPI_INT, c, sendCounts, displacement, MPI_INT,0, MPI_COMM_WORLD);

	if (id == 0) {
		print(a, N);
		print(b, N);
		print(c, N);
	}

	MPI_Finalize();

	return 0;
}