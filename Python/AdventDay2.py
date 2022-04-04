def main():
	print("Python")
	with open('C:\\Users\\21310408\\Desktop\\Codes\\AdventOfCode2021\\inputDay2.txt') as f:
		data = f.readlines()
	horizontalPos = 0
	depth = 0
	for i, line in enumerate(data):
		x = int(line[line.find(' '):])
		if line.startswith("forward"):
			horizontalPos += x
		elif line.startswith("down"):
			depth += x
		else: depth -= x
	print(f'Part 1 = {horizontalPos*depth}\n')
	horizontalPos = 0
	depth = 0
	aim = 0
	for i, line in enumerate(data):
		x = int(line[line.find(' '):])
		if line.startswith("forward"):
			horizontalPos += x
			depth += aim * x
		elif line.startswith("down"):
			aim += x
		else: aim -= x
	print(f'Part 2 = {horizontalPos*depth}\n')
if __name__ == '__main__':
	main()