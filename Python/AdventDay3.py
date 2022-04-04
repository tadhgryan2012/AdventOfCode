def main():
	print("Python")
	with open('C:\\Users\\21310408\\Desktop\\Codes\\AdventOfCode2021\\inputDay3.txt') as f:
		data = f.readlines()
	for i, value in enumerate(data):
		data[i] = value.strip()
	gammaRate = 0
	epsilonRate = 0
	for i in range(12):
		gammaRate = (gammaRate * 2) + int(GetRate(data, 0, i))
	for i in range(12):
		epsilonRate = (epsilonRate * 2) + int(GetRate(data, 1, i))
	print(f"Part 1: {gammaRate*epsilonRate}")
	oxygenGenRating = GetRating(data, 0)
	CO2ScrubberRating = GetRating(data, 1)
def GetRate(data, rate, bit):
	num = 0
	for line in data:
		if not line == "":
			line = line.strip()
			num += int(line[bit:bit+1])
	num //= len(data)/2
	if rate == 1:
		if num == 0: return 1
		else: return 0
	else: return int(num)
def GetRating(data, rate):
	tempData = [0]*len(data)
	for i, value in enumerate(data):
		tempData[i] = value
	for i in range(len(data[0])):
		commonBit = GetRate(tempData, rate, i)
		tempData2 = [0]*len(tempData)
		for i, value in enumerate(tempData):
			tempData2[i] = value
		for line, value in enumerate(tempData2):
			print(value[i:i+1])
			acValue = int(value[i:i+1])
			if acValue != commonBit:
				tempData.pop(line)
	print(tempData)
	return tempData
if __name__ == '__main__':
    main()