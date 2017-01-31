package com.example.afomic.edulearn.data;

import android.content.Context;
import android.util.Log;

import com.example.afomic.edulearn.Constants;

import java.util.ArrayList;

/**
 * Created by afolabi michael on 16-Jul-16.
 *
 */
public class ModelData {
    NoteDatabase database;
    Context mContext;

    
    public ModelData(Context context){
        mContext=context;
        database=new NoteDatabase(context);
        if(database.isEmpty()){
            Log.e(Constants.TAG, "ModelData: add data" );
            addDummyData();
        }

    }
  
    public void addNote(Note note){
        database.addNote(note);
    }
    public void addNotes(ArrayList<Note> notes){
        database.addNotes(notes);
    }
    public void updateNote(String topic,String note,String course){
        database.updateNote(course, topic, note);
    }
    public void updateSpan(String course,String topic,String span){
        database.updateSpan(course,topic,span);
    }
    public void addDummyData(){
        ArrayList<Note> dummyNotes=new ArrayList<>();
        dummyNotes.add(new Note("<h2>Measurement</h2><p>The classes and interfaces of the collections framework are members of package" +
                "java.util. In the next section, we begin our discussion by examining the collections framework" +
                "capabilities for array manipulation. In earlier versions of Java, the classes in the" +
                "collections framework stored and manipulated Object references, enabling you to store" +
                "any object in a collection.</p><p> One inconvenient aspect of this approach occurs when" +
                "retrieving Object references from a collection. A program normally needs to process specific" +
                "types of objects. As a result, the Object references obtained from a collection typically" +
                "need to be cast to an appropriate type to allow the program to process the objects correctly." +
                "In Java SE 5, the collections framework was enhanced with the generics capabilities we" +
                "introduced in Chapter 7 when discussing generic ArrayLists. This means that you can" +
                "specify the exact type that will be stored in a collection.</p><p> You also receive the benefits of compile-" +
                "time type checking—the compiler ensures that you’re using appropriate types with your" +
                "collection and, if not, issues compile-time error messages. Also, once you specify the type" +
                "stored in a collection, any reference you retrieve from the collection will have the specified" +
                "type. This eliminates the need for explicit type casts that can throw ClassCastExceptions if" +
                "the referenced object is not of the appropriate type. In addition, the generic collections are" +
                "backward compatible with Java code that was written before generics were introduced.</p>", "Measurement", "chemistry",""));

        dummyNotes.add(new Note("<h3>Fire</h3><p>In addition to setting up the method calls, the compiler also determines whether the" +
                "operations in the method body can be applied to elements of the type stored in the array" +
                "argument. The only operation performed on the array elements in this example is to" +
                "output their String representation. Line 26 performs an implicit toString call on every" +
                "element. To work with generics, every element of the array must be an object of a class or interface" +
                "type. Since all objects have</p>  <p>toString method, the compiler is satisfied that line 26" +
                "performs a valid operation for any object in printArray’s array argument. The toString" +
                "methods of classes Integer, Double and Character return the String representation of" +
                "the underlying int, double or char value, respectively.</p>" +
                "<h4>Erasure at Compilation Time</h4>" +
                "<p>When the compiler translates generic method printArray into Java byte codes, it removes" +
                "the type-parameter section and replaces the type parameters with actual types. This process is" +
                "known as erasure. By default all generic types are replaced with type Object. So the compiled" +
                "version of method printArray appears as shown in Fig. 21.4—there’s only one copy" +
                "of this code, which is used for all printArray calls in the example. This is quite different" +
                "from other, similar mechanisms, such as C++’s templates, in which a separate copy of the" +
                "source code is generated and compiled for every type passed as an argument to the method." +
                "As you’ll see in Section 21.4, the translation and compilation of generics is a bit more involved" +
                "than what we’ve discussed in this section.</p>" +
                "<p>By declaring printArray as a generic method in Fig. 21.3, we eliminated the need for" +
                "the overloaded methods of Fig. 21.1, saving 19 lines of code and creating a reusable" +
                "method that can<p> output the String representations of the elements in any</p> array that contains" +
                "objects. However, this particular example could have simply declared the printArray" +
                "method as shown in Fig. 21.4, using an Object array as the parameter. This would have" +
                "yielded the same results, because any Object can be output as a String. In a generic" +
                "method, the benefits become apparent when the method also uses a type parameter as the" +
                "method’s return type, as we demonstrate in the next section.</p>", "Fire", "chemistry",""));
        dummyNotes.add(new Note("Let’s consider a generic method example in which type parameters are used in the return" +
                "type and in the parameter list (Fig. 21.5). The application uses a generic method maximum" +
                "to determine and return the largest of its three arguments of the same type. Unfortunately," +
                "the relational operator > cannot be used with reference types. However, it’s possible to compare" +
                "two objects of the same class if that class implements the generic interface Comparable<" +
                "T> (package java.lang). All the type-wrapper classes for primitive types implement" +
                "this interface. Like generic classes, generic interfaces enable you to specify, with a single" +
                "interface declaration, a set of related types. Comparable<T> objects have a compareTo" +
                "method. For example, if we have two Integer objects, integer1 and integer2, they can" +
                "be compared with the expression:" +
                "It’s your responsibility when you declare a class that implements Comparable<T> to declare method" +
                "compareTo such that it compares the contents of two objects of that class and returns the comparison" +
                "results. As specified in interface Comparable<T>’s documentation, compareTo must" +
                "return 0 if the objects are equal, a negative integer if object1 is less than object2 or a positive" +
                "integer if object1 is greater than object2. For example, class Integer’s compareTo" +
                "method compares the int values stored in two Integer objects. A benefit of implementing" +
                "interface Comparable<T> is that Comparable<T> objects can be used with the sorting and" +
                "searching methods of class Collections (package java.util). We discussed those methods" +
                "in Chapter 20. In this example, we’ll use method compareTo in method maximum to" +
                "help determine the largest value.", "Light", "chemistry",""));
        addNotes(dummyNotes);
        String word="type and in the parameter list (Fig. 21.5). The application uses a generic method maximum" +
                "to determine and return the largest of its three arguments of the same type. Unfortunately," +
                "the relational operator > cannot be used with reference types. However, it’s possible to compare" +
                "two objects of the same class if that class implements the generic interface Comparable<" +
                "T> (package java.lang). All the type-wrapper classes for primitive types implement" +
                "this interface. Like generic classes, generic interfaces enable you to specify, with a single" +
                "interface declaration, a set of related types. Comparable<T> objects have a compareTo" +
                "method. For example, if we have two Integer objects, integer1 and integer2, they can" +
                "be compared with the expression:" +
                "It’s your responsibility when you declare a class that implements Comparable<T> to declare method" +
                "compareTo such that it compares the contents of two objects of that class and returns the comparison" +
                "results. As specified in interface Comparable<T>’s documentation, compareTo must" +
                "return 0 if the objects are equal, a negative integer if object1 is less than object2 or a positive" +
                "integer if object1 is greater than object2. For example, class Integer’s compareTo" ;

        Note note=new Note(word,"Chemical Reaction","chemistry","");
        addNote(note);

    }
    public Note getNote(String course,String topic){
       return database.getNote(course,topic);
    }

}
