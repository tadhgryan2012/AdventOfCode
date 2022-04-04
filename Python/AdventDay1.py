def main():
    print("Python")
    with open('C:\\Users\\21310408\\Desktop\\Codes\\AdventOfCode2021\\inputDay1.txt') as f:    
        stringData = f.readlines()
    data = []
    for i, num in enumerate(stringData):
        data.append(int(num))
    numLarger = 0
    for i, num in enumerate(data):
        if i > 0:
            if num > data[i-1]:
                numLarger += 1
    print(f'Part 1:\nIncreases {numLarger} times.')
    numLarger = 0
    for i, num in enumerate(data):
        if i > 2:
            if num > data[i-3]:
                numLarger += 1
    print(f'Part 2:\nIncreases {numLarger} times.')
if __name__ == '__main__':
    main()