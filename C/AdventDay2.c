#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
void main() {
	printf("C\n");
	FILE *file = fopen("C:\\Users\\21310408\\Desktop\\Codes\\AdventOfCode2021\\inputDay2.txt", "r");
	char ** instruction[1000][7];
	char ** amount[1000][1];
	int dataSize = sizeof(instruction)/sizeof(instruction[0]);
	int numIncreased = 0;
	for (int i=0; i<dataSize; i++) {
		fscanf(file, "%s", &instruction[i]);
		fscanf(file, "%s", &amount[i]);
	}
	fclose(file);
	int horizontalPos = 0;
	int depth = 0;
	for (int i=0; i<dataSize; i++) {
		int x = atoi(amount[i]);
		if (strcmp(instruction[i], "forward") == 0) horizontalPos += x;
		else if (strcmp(instruction[i], "down") == 0) depth += x;
		else depth -= x;
	}
	printf("Part 1 = %d\n", horizontalPos*depth);
	horizontalPos = 0;
	depth = 0;
	int aim = 0;
	for (int i=0; i<dataSize; i++) {
		int x = atoi(amount[i]);
		if (strcmp(instruction[i], "forward") == 0) {
			horizontalPos += x;
			depth += aim*x;
		} else if (strcmp(instruction[i], "down") == 0) aim += x;
		else aim -= x;
	}
	printf("Part 2 = %d\n", horizontalPos*depth);
}