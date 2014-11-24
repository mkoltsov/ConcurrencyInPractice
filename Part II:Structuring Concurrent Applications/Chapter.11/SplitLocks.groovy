class LockSplit {
    def usersList = ["chef",'pupa']
    def queryList = ["add",'remove']

    def addUser (name){
        synchronized (usersList){
            usersList.add(name)
        }
    }

    def addQuery (name){
        synchronized (queryList){
            queryList.add(name)
        }
    }

}