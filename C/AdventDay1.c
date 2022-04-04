#include <stdio.h> 
void main() {
	printf("C\n");
	FILE *file = fopen("C:\\Users\\21310408\\Desktop\\Codes\\AdventOfCode2021\\inputDay1.txt", "r");
	int data[2000];
	int dataSize = sizeof(data)/sizeof(data[0]);
	int numIncreased = 0;
	for (int i=0; i<dataSize; i++) {
		fscanf(file, "%d", & data[i]);
	}
	fclose(file);
	for (int i=1; i<dataSize; i++) {
		if (data[i]>data[i-1]) {
			numIncreased++;
		}
	}
	printf("Part 1:\nIncreases %d times.\n", numIncreased);
	numIncreased = 0;
	for (int i=3; i<dataSize; i++) {
		if (data[i]>data[i-3]) {
			numIncreased++;
		}
	}
	printf("Part 2:\nIncreases %d times.\n", numIncreased);
}