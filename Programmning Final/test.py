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


def paths(tree):
  #Helper function
  #receives a tree and 
  #returns all paths that have this node as root and all other paths
  if tree is None:
    return ([], [])
  else: #tree is a node
    root = tree.value
    left_rooted_paths = [[root]]
    left_unrooted_paths = []
    (useable, unuseable) = paths(tree.left)
    for path in useable:
        left_unrooted_paths.append(path)
        left_rooted_paths.append([root]+path)
    for path in unuseable:
        left_unrooted_paths.append(path)
        
    right_rooted_paths = [[root]]
    right_unrooted_paths = []
    (useable, unuseable) = paths(tree.right)
    for path in useable:
        right_unrooted_paths.append(path)
        right_rooted_paths.append([root]+path)
    for path in unuseable:
        right_unrooted_paths.append(path)
    return (left_rooted_paths + right_rooted_paths, left_unrooted_paths + right_unrooted_paths)

    

def countUniqueSums(inputString):
    nums = list(map(int, inputString))
    root = buildBST(nums)
    (left_rooted_paths,  right_rooted_paths) = paths(root)
    # Calculate unique sums for every pair of nodes in the BST
    all_left_paths = left_rooted_paths 
    all_right_paths = right_rooted_paths
    all_left_paths_sum = []
    all_right_paths_sum = []
    for path in all_left_paths:
        all_left_paths_sum.append(sum(path)) 
    for path in all_right_paths:
        all_right_paths_sum.append(sum(path))
    for i in range(len(all_right_paths_sum)):
        for j in range(len(all_left_paths_sum)):
            sums.add(all_right_paths_sum[i] + all_left_paths_sum[j])
    print(sums)

print(countUniqueSums("31415926"))