# VolleyLibrary
This library make the api call easy using the Volley Framework. Current version availble is 0.0.1

<B>Installing</B>

###Maven Add the following maven dependency exchanging x.x.x for the latest release.


<div class="highlight highlight-text-xml"><pre>&lt;<span class="pl-ent">dependency</span>&gt;
    &lt;<span class="pl-ent">groupId</span>&gt;com.jeeva&lt;/<span class="pl-ent">groupId</span>&gt;
    &lt;<span class="pl-ent">artifactId</span>&gt;volley-library&lt;/<span class="pl-ent">artifactId</span>&gt;
    &lt;<span class="pl-ent">version</span>&gt;x.x.x&lt;/<span class="pl-ent">version</span>&gt;
    &lt;<span class="pl-ent">type</span>&gt;pom&lt;/<span class="pl-ent">type</span>&gt;
&lt;/<span class="pl-ent">dependency</span>&gt;</pre></div>




###Gradle Add the following gradle dependency exchanging x.x.x for the latest release.



<div class="highlight highlight-source-groovy"><pre>dependencies {
    compile <span class="pl-s"><span class="pl-pds">'</span>com.jeeva:volley-library:x.x.x<span class="pl-pds">'</span></span>
}</pre></div>


    
    
       
    
       
###Cloning First of all you will have to clone the library.

git clone https://github.com/Jeevs2nan/VolleyLibrary.git


Now that you have the library you will have to import it into Android Studio. In Android Studio navigate the menus like this.

File -> Import Project ...


In the following dialog navigate to VolleyLibrary which you cloned to your computer in the previous steps and select the build.gradle.


<B> Get Started</B>

Need to implement the IResponseListener<E> interface with the enum class which we are going to use for holding the api names for our refrence. 
In the below class I have APIRequest enum which can be find below:

public class MainActivity extends AppCompatActivity implements IResponseListener<APIRequest> {

private String url = "YOUR_URL";
Map<String, String> yourHeader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "28");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        
        IRequestHandler iRequestHandler = RequestHandler.getInstance();
        
        //Setting header, content type, retry policy and tag are optional and it is based on your need...
        iRequestHandler.setRequestHeaders(yourHeader);
        iRequestHandler.setContentType("application/json;charset=UTF-8");
        
        iRequestHandler.jsonObjectRequest(this, Request.Method.POST, url, jsonObject, this, APIRequest.LOGIN, null);


    }

    @Override
    public void successResponse(String successResponse, APIRequest apiRequest) {

    }

    @Override
    public void successResponse(JSONObject jsonObject, APIRequest apiRequest) {

        switch (apiRequest) {
            case LOGIN:
                Log.e("Log::", "::"+jsonObject.toString());

               
                break;
        }

    }

    @Override
    public void errorResponse(String errorResponse, APIRequest apiRequest) {

    }

    @Override
    public void removeProgress(Boolean hideFlag) {

    }
}

<p>Below is the enum class which is used to segrate the api call and holds a name for each and every api. 
This enum is purely developer based .i.e., user defined. 
In this example I am having the below enum (APIRequest) which holds the login api name.</p>


public enum APIRequest {

    LOGIN
}


<B>Contribution</B>

<p>Contributions are welcome, since the library is in development phase.</p>




