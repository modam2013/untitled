
import org.example.ElementNotFoundException;
import org.example.InvalidArgumentException;
import org.example.StringListIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.Objects;



public class StringArrayList implements StringList{
    private String[]data;
    private int currentSize;
    public StringArrayList(int size){
        data=new String[size];
        currentSize=0;
    }
    public StringArrayList(String... args){
        data=new String[args.length];
        System.arraycopy(args,0,data,0,args.length);
        currentSize=data.length;
    }
    @Override
    public String add(String item){
        if (currentSize==data.length){
            resize(currentSize +1);
        }
        data[currentSize]=item;
        currentSize++;
        return item;
    }
    @Override
    public String add(int index,String item){
        chckBounds(index);
        if (currentSize==data.length){
           resize(currentSize+1);
        }
        for (int i=currentSize;i>index;i--){
            data[i]=data[i-1];
        }
        data[index]=item;
        currentSize++;
        return item;
    }

    @Override
    public String set(int index, String item) {
      chckBounds(index);
      data[index]=item;
      return item;
    }

    @Override
    public String remove(String item){
        int index=indexOf(item);
        if (index==-1){
            throw new ElementNotFoundException();
        }
        return remove(index);
    }
    @Override
    public String remove(int index){
        chckBounds(index);
        String result=data[index];
        for (int i=index+1;i<currentSize;i++){
            data[i-1]=data[i];
        }
        currentSize--;
        return result;
    }
    @Override
    public boolean contains(String item){
        return indexOf(item)!=-1;
    }
    @Override
    public int indexOf(String item){
        for (int i=0;i<currentSize;i++){
            if (data[i].equals(item)){
                return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(String item){
        for (int i=currentSize-1;i>=0;i--){
            if (data[i].equals(item)){
                return i;
            }
        }
        return -1;
    }
    @Override
    public String get(int index){
        chckBounds(index);
        return data[index];
    }
    @Override
    public int size(){
        return currentSize;
    }
    @Override
    public boolean isEmpty(){
        return currentSize==0;
    }
    @Override
    public void clear(){
        Arrays.fill(data,0,currentSize,null);
        currentSize=0;
    }
    @Override
    public String[] toArray(){
        String[]array=new String[currentSize];
        System.arraycopy(data,0,array,0,currentSize);
        return array;
    }
    @Override
    public boolean eguals(StringList other) {
        if (other==null){
            throw new InvalidArgumentException();
        }
        if (currentSize!=other.size()){
            return false;
        }
        for (int i=0;i<currentSize;i++){
            if (!Objects.equals(data[i],other.get(i) )){
                return false;
            }
        }
        return true;
    }
    private void chckBounds(int index){
        if (index<0||index>=currentSize){
            throw new StringListIndexOutOfBoundsException();
        }
    }
    private void resize(int newSize){
        int size=currentSize *2;
        size=Math.max(size,newSize);
        String[]newData=new String[size];
        System.arraycopy(data,0,newData,0,currentSize);
        data=newData;
    }
}
