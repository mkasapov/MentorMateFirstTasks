package reflectionTask;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ReflectionFinal {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        SourcePerson sourcePerson = new SourcePerson("martin", 22, "blg", (short) 3,
                new Student("Ivan", "Ivanov", 15, 3.3),
                new Example("Goran", 77, "randomText"));
        DestinationPerson destinationPerson = new DestinationPerson();


        System.out.println("Student info");
        System.out.println("----SourcePerson----");
        System.out.println(sourcePerson.getStudent());
        System.out.println(sourcePerson.getAge());
        System.out.println("----DestinationPerson----");
        System.out.println(destinationPerson.getStudent());
        System.out.println(destinationPerson.getAge());
        System.out.println("Example info");
        System.out.println(sourcePerson.getExample());
        System.out.println(sourcePerson.getExample().getExampleName());
        System.out.println(destinationPerson.getExample());
        deepCopyGeneric(sourcePerson, destinationPerson);
        System.out.println();
        System.out.println("DEEP COPY");
        System.out.println(sourcePerson.getStudent());
        System.out.println(sourcePerson.getAge());
        System.out.println(sourcePerson.getStudent().getFirstName());
        System.out.println(destinationPerson.getStudent());
        System.out.println(destinationPerson.getAge());
        System.out.println(destinationPerson.getStudent().getFirstName());
        System.out.println("----Example info----");
        System.out.println(sourcePerson.getExample());
        System.out.println(sourcePerson.getExample().getExampleName());
        System.out.println(destinationPerson.getExample());
        System.out.println(destinationPerson.getExample().getExampleName());

    }

    //GENERIC SOLUTION
    static void deepCopyGeneric(Object sourceObj, Object destObj) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        utility(sourceObj, destObj);
        List<Object> objectTypeFieldsSource = objectTypeFields(sourceObj);
        List<Object> objectTypeFieldsDestination = objectTypeFields(destObj);

        for (Object objFieldSrc : objectTypeFieldsSource) {
            for (Object objFieldDest : objectTypeFieldsDestination) {

                if (objFieldSrc.getClass().equals(objFieldDest.getClass())) {

                    deepCopy(objFieldSrc, objFieldDest);
                }
            }
        }

    }

    static Object returnFieldTypeObject(Field sourceField, Object sourceObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String fieldClassName = "";
        Package pack = sourceObj.getClass().getPackage();
        String packageName = pack.getName();
        String fieldTypeName = sourceField.getType().getTypeName();
        if (fieldTypeName.contains(packageName)) {
            fieldClassName = sourceField.getType().getName().split("\\.")[1];
        }
        if (!fieldClassName.equals("")) {
            Class<?> desiredClass = Class.forName(fieldTypeName);
            Class<?> objClass = sourceObj.getClass();
            if (sourceField.getType().isAssignableFrom(desiredClass)) {
                Method fieldGetter = objClass.getDeclaredMethod("get" + fieldClassName);
                if (fieldGetter.invoke(sourceObj) != null) {
                    return fieldGetter.invoke(sourceObj);
                } else {
                    Method fieldSetter = objClass.getDeclaredMethod("set" + fieldClassName, desiredClass);
                    Constructor<?> constructor = desiredClass.getDeclaredConstructors()[0];
                    Object newObj = constructor.newInstance();
                    fieldSetter.invoke(sourceObj, newObj);
                    return fieldGetter.invoke(sourceObj);
                }
            }
        }
        return null;
    }

    static List<Object> objectTypeFields(Object sourceObject) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        List<Object> listWithObjects = new ArrayList<>();
        Class<?> sourceClass = sourceObject.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            Object fieldObj = returnFieldTypeObject(sourceField, sourceObject);
            if (fieldObj != null) {
                listWithObjects.add(fieldObj);
            }
        }
        return listWithObjects;
    }

    static boolean isObjectType(Field srcField, Object srcObj) throws ClassNotFoundException {
        String fieldClassName = "";
        Package pack = srcObj.getClass().getPackage();
        String packageName = pack.getName();
        String fieldTypeName = srcField.getType().getTypeName();
        if (fieldTypeName.contains(packageName)) {
            fieldClassName = srcField.getType().getName().split("\\.")[1];
        }
        if (!fieldClassName.equals("")) {
            Class<?> desiredClass = Class.forName(fieldTypeName);
            Class<?> objClass = srcObj.getClass();
            if (srcField.getType().isAssignableFrom(desiredClass))
                return true;
        }
        return false;
    }

    //...OtherStuff
    static void deepCopy(Object sourceObj, Object destinationObj) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        if (checkForPrivateStudentField(sourceObj, destinationObj)) {

            utility(sourceObj, destinationObj);
            Class<?> c = Class.forName("reflectionTask.Student");
            Constructor<?> constructor = c.getDeclaredConstructors()[0];
            Object newStudentObj = constructor.newInstance();
            Object sourceStudent = returnStudentObj(sourceObj);
            Object destinationStudent = returnStudentObj(destinationObj);
            utility(sourceStudent, newStudentObj);
            utility(newStudentObj, destinationStudent);

        } else {
            utility(sourceObj, destinationObj);
        }
    }

    static void utility(Object sourceObject, Object destinationObject) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> sourceClass = sourceObject.getClass();
        Method[] sourceMethods = sourceClass.getDeclaredMethods();
        Field[] sourceFields = sourceClass.getDeclaredFields();

        Class<?> destinationClass = destinationObject.getClass();
        Method[] destinationMethods = destinationClass.getDeclaredMethods();
        Field[] destinationFields = destinationClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field destinationField : destinationFields) {

                if (namesAreSame(sourceField, destinationField)
                        && typesAreSame(sourceField, destinationField)
                        && !isObjectType(destinationField, destinationObject)) {
                    String fieldName = sourceField.getName();
                    String getterName = createGetterName(fieldName);
                    String setterName = createSetterName(fieldName);
                    invokeSrcGettersAndSetToDest(sourceObject, destinationObject, sourceMethods, destinationMethods, getterName, setterName);
                }
            }
        }
    }

    static void shallowCopy(Object sourceObject, Object destinationObject) throws InvocationTargetException, IllegalAccessException {
        Class<?> sourceClass = sourceObject.getClass();
        Method[] sourceMethods = sourceClass.getDeclaredMethods();
        Field[] sourceFields = sourceClass.getDeclaredFields();

        Class<?> destinationClass = destinationObject.getClass();
        Method[] destinationMethods = destinationClass.getDeclaredMethods();
        Field[] destinationFields = destinationClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field destinationField : destinationFields) {
                if (namesAreSame(sourceField, destinationField) && typesAreSame(sourceField, destinationField)) {
                    String fieldName = sourceField.getName();
                    String getterName = createGetterName(fieldName);
                    String setterName = createSetterName(fieldName);
                    invokeSrcGettersAndSetToDest(sourceObject, destinationObject, sourceMethods, destinationMethods, getterName, setterName);
                }
            }
        }
    }

    private static void invokeSrcGettersAndSetToDest(Object sourceObject, Object destinationObject, Method[] sourceMethods, Method[] destinationMethods, String getterName, String setterName) throws IllegalAccessException, InvocationTargetException {
        for (Method sourceMethod : sourceMethods) {
            if (sourceMethod.getName().equals(getterName)) {
                var value = sourceMethod.invoke(sourceObject);
                invokeDestinationSetters(destinationObject, destinationMethods, setterName, value);
            }
        }
    }

    private static void invokeDestinationSetters(Object destinationObject, Method[] destinationMethods, String setterName, Object value) throws IllegalAccessException, InvocationTargetException {
        for (Method destinationMethod : destinationMethods) {
            if (destinationMethod.getName().equals(setterName)) {
                destinationMethod.invoke(destinationObject, value);
            }
        }
    }

    static String createGetterName(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    static String createSetterName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    static boolean namesAreSame(Field first, Field second) {
        return first.getName().compareTo(second.getName()) == 0;
    }

    static boolean typesAreSame(Field first, Field second) {
        return first.getType().equals(second.getType());
    }

    static boolean modifiersAreSame(Field first, Field second) {

        return first.getModifiers() == second.getModifiers();
    }

    static boolean checkForPrivateStudentField(Object srcObject, Object destObject) {
        Class<?> sourceClass = srcObject.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();

        Class<?> destinationClass = destObject.getClass();
        Field[] destinationFields = destinationClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            for (Field destinationField : destinationFields) {

                int modifier = destinationField.getModifiers();

                if (namesAreSame(sourceField, destinationField)
                        && typesAreSame(sourceField, destinationField)
                        && modifiersAreSame(sourceField, destinationField)
                        && Modifier.isPrivate(modifier)
                        && Student.class.isAssignableFrom(destinationField.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    static Student returnStudentObj(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] objFields = objClass.getDeclaredFields();

        for (Field objField : objFields) {
            int modifier = objField.getModifiers();

            if (objField.getName().equals("student")
                    && Modifier.isPrivate(modifier)
                    && Student.class.isAssignableFrom(objField.getType())) {

                Method studentGetter = objClass.getDeclaredMethod("getStudent");
                if (studentGetter.invoke(obj) != null) {
                    return (Student) studentGetter.invoke(obj);
                } else {
                    Method studentSetter = objClass.getDeclaredMethod("setStudent", Student.class);
                    Student student = new Student();
                    studentSetter.invoke(obj, student);
                    return (Student) studentGetter.invoke(obj);
                }
            }
        }
        return null;
    }



}



