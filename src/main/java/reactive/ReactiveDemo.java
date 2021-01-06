package reactive;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class ReactiveDemo {
    public static void main(String[] args) {
//        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
//        Observable.from(input)
//                .filter(x -> x % 2 == 0);

        Observable<Integer> observable = Observable.just("1", "2", "a", "3")
                .map(Integer::parseInt);

        observable
                .onExceptionResumeNext(Observable.just(-1))
//                .retryWhen(observable1 -> Observable.timer(5, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.computation())
                .subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Exception " + throwable.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next integer is " + integer);
            }
        });

        /*Person person = new Person();
        person.setAge(10);
        person.setName("ranjan");

        Observable<String> nameObservable =
                Observable.defer(() -> Observable.just(person.getName()));
        Observable<Integer> ageObservable =
                Observable.defer(() -> Observable.just(person.getAge()));

        nameObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(String name) {
                System.out.println("name is: " + name);
            }
        });

        ageObservable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Integer age) {
                System.out.println("age is: " + age);
            }
        });*/


    }
}

class Person {
    private String name;
    private int age;
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
}

class MySubscriber extends Subscriber<Integer> {

    @Override
    public void onCompleted() {
        System.out.println("MySubscriber, Sequence completed");
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("MySubscriber, Exception " + throwable.getMessage());
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("MySubscriber, Next integer is " + integer);
    }
}
