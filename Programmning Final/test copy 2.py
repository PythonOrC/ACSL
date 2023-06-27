class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

sums = set()
def buildBST(nums):
    root = None
    for num in nums:
        root = insertNode(root, num)
    return root


def insertNode(root, value):
    if root is None:
        return Node(value)
    if value <= root.value:
        root.left = insertNode(root.left, value)
    else:
        root.right = insertNode(root.right, value)
    return root


def calculatePathSums(node, path, sums):
    if node is None:
        return

    # Calculate the sum of the current path
    print(path)
    current_sum = 0
    for i in range(len(path)):
        current_sum += path[i]
        sums.add(current_sum)

    # Recursively traverse left and right subtrees
    calculatePathSums(node.left, path + [node.value], sums)
    calculatePathSums(node.right, path + [node.value], sums)


def countUniqueSums(inputString):
    nums = list(map(int, inputString))
    root = buildBST(nums)

    sums = set()

    # Calculate unique sums for every branch between any two nodes in the BST
    calculatePathSums(root, [], sums)

    return len(sums)



print(countUniqueSums("31415926"))