object ImmutableQueue {

    def reverse[T](l : List[T]) : List[T] = {
        if (l.length==0)
          return Nil
        else
          reverse(l.tail):::List(l.head)
    }


    def printAll(new_q : ImmutableQueue[Int]) : Unit  = {

        try {
            val (element, remaining_q) = new_q.dequeue;
            //print(element+" ");
            printAll(remaining_q);
        } catch {
            case ex: RuntimeException => Unit;
        }
    }


    def main(args : Array[String]) = {

        val q = new ImmutableQueue[Int]();

        val new_q = q.enqueue(1).enqueue(2).enqueue(3).enqueue(4).enqueue(5);

        printAll(new_q);
        print("\n")

    }

}

class ImmutableQueue[T] (val in : List[T] = Nil, val out : List[T] = Nil) {

    def enqueue(x : T) : ImmutableQueue[T] = {
      new ImmutableQueue(x::in, Nil);
    }

    def dequeue : (T, ImmutableQueue[T]) = {
        if(in.length==0 && out.length==0){
        throw new NoSuchElementException
        }
        else if(in.length==0){
          return (out.head, new ImmutableQueue(in,out.tail) )
        }
        else{
        val revin = ImmutableQueue.reverse(in)
        val newout = revin:::out
        return (newout.head,new ImmutableQueue(Nil, newout) )
        }
    }

}
