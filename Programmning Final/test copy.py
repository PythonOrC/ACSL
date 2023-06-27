class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


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

def paths(tree):
    if tree is None:
        return ([], [])
    else:
        root = tree.value
        rooted_paths = [[root]]
        rooted_nodes = [root]
        unrooted_paths = []
        unrooted_nodes = []
        
        (useable, unuseable) = paths(tree.left)
        for path in useable:
            unrooted_paths.append(path)
            unrooted_nodes.extend(path)
            rooted_paths.append([root] + path)
            rooted_nodes.extend([root] + path)
        for path in unuseable:
            unrooted_paths.append(path)
            unrooted_nodes.extend(path)
        
        (useable, unuseable) = paths(tree.right)
        for path in useable:
            unrooted_paths.append(path)
            unrooted_nodes.extend(path)
            rooted_paths.append([root] + path)
            rooted_nodes.extend([root] + path)
        for path in unuseable:
            unrooted_paths.append(path)
            unrooted_nodes.extend(path)
        
        return (rooted_paths, rooted_nodes, unrooted_paths, unrooted_nodes)


def countUniqueSums(inputString):
    nums = list(map(int, inputString))
    root = buildBST(nums)

    sums = set()

    # Calculate unique sums for every branch between any two nodes in the BST
    a, _, b, _ = paths(root)

    for path in a + b:
        sums.add(sum(path))

    return len(sums)




print(countUniqueSums("31415926"))