# android-sample-maverick

Part 4: Conceptual Questions
----------------------------

1. Following are some major differences between Interface and Abstract Class:
	- In Interface, all methods should be abstract methods whereas Abstact Class can have abstract and non-abstract methods.
	- Interface can be implemented so a class can implement multiple interfaces. On the other hand abstract class can be extended so a class can only extend one class becuase Java doesn't allow multiple inhertance.
	- To define Interface "interface" keyword is used. e.g.
        ```java
            public interface A{ public void print(); }
        ```
	- To define Abstract class and methods, "abstract" keyword is used e.g.
		```java
            public abstract class B{ public abstract void print(); }`
		```
2. The main flaw of inhertance in Java is that it doesn't allow multiple inhertance through classes due to the simplicity because multiple inhertance can cause "Diamond Ring Problem" where a class extends two classes and those classes extends the same parent class. To solve this Java provide interfaces which are close to inhertance but could not really fullfil the requirements becuase interface can only have abstract methods.

3. Following are few major differences between Activity and Fragment.
	- Activity is basically a single focused user interface where user can interact with app whereas Fragment is an independent portion of user interface in an Activity.
	- Activity can be works without fragment where as Fragment can only be part of an Activity. One activity can have multiple fragments and fragment can be reused in multiple activities. Fragment's life cycle directly depends on its parent activity's.

4. Although Fragment class has "getActivity" method which returns the instance of parent Activity which can be parsed into desired Activity's object however the problem of this approach is that a fragment can only be used with a single Activity so the best way to communicate from Fragment to Activity is thorugh interfaces. This way a fragment can be tottaly reuseable as long as parent activity implements the required interface. Explained below


```java
    // Parent Activity must implement this interface
	public interface OnItemSelectedListener {
		public void onItemSelected(Item i);
	}

	// Activity Class which should implement the Interface
	public class B extends Activity implement OnItemSelectedListener{
		...
		@Override
		public void onItemSelected(Item i){
			Log.i('A', 'Item Selected');
		}
		...
	}

	//	Fragment Class which uses the interface to communicate with Activity
	public static class A extends Fragment {
		OnItemSelectedListener mCallback;
		...
			 @Override
			public void onAttach(Activity activity) {
				super.onAttach(activity);
				// This makes sure that the container activity has implemented
				// the callback interface. If not, it throws an exception
				try {
					mCallback = (OnItemSelectedListener) activity;
				} catch (ClassCastException e) {
					throw new ClassCastException(activity.toString()
						+ " must implement OnItemSelectedListener");
				}
			}
		...
	}
```
5. Yes an entire application can be developed without using Fragments however it is highly recommended to use them as it can drastically improve the User experience and also can really be helpful with the development process.
If an app is developed for tablets and phone and because tablet have more screen so usually they have more room to combine and interchange UI components so we can show two fragments side by side on tablet and one fragment at a time on mobile phones. This can be easily achived by using the Fragments.

6. Some issues with AsyncTask are:
	- Most common issue that developer face using AsyncTask is during orientation change. When orientation is changed and if activity is restarted (based of the layout changes and other factors) then current running task won't have the instance of current activity which can cause problem.
	- After the Android 1.5 android supports only 10 task at the same time.
	- Developer needs to manualy handle the cancelation of AsyncTask by checking the flag after each statment.
	