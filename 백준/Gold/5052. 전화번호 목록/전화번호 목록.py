class Tree(object):
    def __init__(self, num):
        self.name = num
        self.children = []
        self.end = False
    def check(self):
        if self.end == True:
            return True
        else:
            return False
    def add_child(self, node):
        for child in self.children:
            if child.get_name() == node.get_name():
                return child
        self.children.append(node)
        return node
    def has_child(self):
        if self.children:
            return True
        else:
            return False
    def get_name(self):
        return self.name
    
    def now_end(self):
        self.end = True



tests = int(input())

def go():
    answer = ""
    nums = int(input())
    for num in range(nums):
        temp = str(input())
        curr = None
        for tree in trees:
            if tree.get_name() == temp[0]:
                curr = tree
        if curr == None:
            curr = Tree(temp[0])
            trees.append(curr)
        
        for i in range(1, len(temp)):
            if curr.check():
                if not answer:
                    answer = "NO"
            child = Tree(temp[i])
            curr = curr.add_child(child)
        curr.now_end()
        if curr.has_child():
            if not answer:
                answer = "NO"
    if not answer:
        answer = "YES"
    return answer
for test in range(tests):
    trees = []
    print(go())