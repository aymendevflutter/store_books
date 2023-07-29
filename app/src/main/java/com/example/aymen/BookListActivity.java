package com.example.aymen;

import static com.example.aymen.fullprice.price.fullprice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aymen.adapters.BookListAdapter;
import com.example.aymen.objects.Book;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private ListView bookListView;
    private List<Book> bookList;
    private BookListAdapter bookListAdapter;
    private TextView Fullprice;
    private Button seecart;
    private String categ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Fullprice = findViewById(R.id.total_price_text);

        try {
            categ = getIntent().getStringExtra("categ");
        }catch (Exception e){

        }
        double number = fullprice;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(number);

        String showprice = "Toltal :" + formattedNumber +"$";
        Fullprice.setText( showprice);


        seecart = findViewById(R.id.seecart);
        seecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotocart();



            }
        });

        String selectedCategory;
        // Retrieve the selected category from the intent
        if(categ == null){
         selectedCategory =  getIntent().getStringExtra("category");}
        else {
            selectedCategory =categ;
        }

        // Fetch the books for the selected category
        bookList = getBooksByCategory(selectedCategory);

    //    if(!check){

     //   price.listMemory.addAll(bookList);

      //      bookList = getBooksByCategory(selectedCategory);
      //  }else {
      //      bookList.addAll(price.listMemory) ;
      //  }

                // Set up the ListView and adapter
        bookListView = findViewById(R.id.book_list_view);
        bookListAdapter = new BookListAdapter(this, bookList);
        bookListView.setAdapter(bookListAdapter);

        // Handle item click events
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook = bookList.get(position);
                // Launch DetailActivity or perform any other action when a book is clicked
                launchDetailActivity(selectedBook);
            }
        });
    }

    // Replace this method with your own logic to fetch books by category
    private List<Book> getBooksByCategory(String category) {
        List<Book> filteredList = new ArrayList<>();
        List<Book> allBooks = getBookList(); // Replace with your own logic to fetch all books

        for (Book book : allBooks) {
            if (book.getCategory().equals(category)) {
                filteredList.add(book);
            }
        }

        return filteredList;
    }




    private void launchDetailActivity(Book selectedBook) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("item_picture",selectedBook.getResourcimage());


        intent.putExtra("title",selectedBook.getTitle());
        intent.putExtra("item_author",selectedBook.getAuthor());
        intent.putExtra("item_price",selectedBook.getPrice());
        intent.putExtra("desc",selectedBook.getDesc());
        intent.putExtra("categ",selectedBook.getCategory());
        startActivity(intent);
        finish();




        // Implement your code to launch the DetailActivity and pass the selected book
    }


    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();

        // Fictional Books
        bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fictional", 12.99,R.drawable.to_kill_a_mockingbird, "A gripping novel set in the 1930s, dealing with racial injustice and moral growth."));
        bookList.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fictional", 9.99,R.drawable.thegreat, "A tragic love story set in the Roaring Twenties, exploring themes of wealth, ambition, and the American Dream."));
        bookList.add(new Book("Pride and Prejudice", "Jane Austen", "Fictional", 8.99,R.drawable.pride, "A classic romance novel revolving around the social norms, love, and marriage in 19th-century England."));
        bookList.add(new Book("1984", "George Orwell", "Fictional", 11.99,R.drawable.alf, "A dystopian novel depicting a totalitarian society ruled by Big Brother, where individualism is suppressed."));

        // Comics
        bookList.add(new Book("Saga", "Brian K. Vaughan", "Comics", 14.99,R.drawable.saga, "An epic space opera following the lives of two lovers from long-warring extraterrestrial races, on the run from authorities."));
        bookList.add(new Book("Watchmen", "Alan Moore", "Comics", 15.99,R.drawable.watchmen, "A groundbreaking graphic novel that deconstructs the superhero genre while exploring themes of power, morality, and identity."));
        bookList.add(new Book("Maus", "Art Spiegelman", "Comics", 11.99,R.drawable.maus, "A Pulitzer Prize-winning graphic novel that recounts the author's father's experiences as a Holocaust survivor."));
        bookList.add(new Book("Batman: The Dark Knight Returns", "Frank Miller", "Comics", 13.99,R.drawable.batman_the_dark_knight_returns, "A gritty Batman story set in a dystopian future, where an aging Bruce Wayne dons the cape and cowl once again."));

        // Biography
        bookList.add(new Book("Becoming", "Michelle Obama", "Autobiography", 12.99,R.drawable.becoming, "The memoir of former First Lady Michelle Obama, reflecting on her personal and professional journey in life and politics."));
        bookList.add(new Book("Steve Jobs", "Walter Isaacson", "Autobiography", 14.99,R.drawable.steve_jobs, "An authorized biography of Steve Jobs, co-founder of Apple Inc., providing insights into his life, work, and innovation."));
        bookList.add(new Book("The Diary of a Young Girl", "Anne Frank", "Autobiography", 10.99,R.drawable.thedirary, "The diary of Anne Frank, a Jewish girl hiding from the Nazis during World War II, offering a firsthand account of life in hiding."));
        bookList.add(new Book("The Extraordinary Life of Nikola Tesla", "Nikola Tesla", "Autobiography", 11.99,R.drawable.the_extraordinary_life_of_nikola_tesla, "A biography of Nikola Tesla, a renowned inventor and electrical engineer, known for his contributions to modern technology."));

        // Self-Help
        bookList.add(new Book("Atomic Habits", "James Clear", "Self Help", 14.99,R.drawable.atomic, "A guide to building good habits and breaking bad ones, providing practical strategies for personal and professional growth."));
        bookList.add(new Book("Mindset: The New Psychology of Success", "Carol S. Dweck", "Self Help", 13.99,R.drawable.mindset_the_new_psychology_of_success, "An exploration of the mindset that leads to success, emphasizing the importance of a growth mindset over a fixed mindset."));
        bookList.add(new Book("The 7 Habits of Highly Effective People", "Stephen R. Covey", "Self Help", 12.99,R.drawable.seven, "A self-help book outlining seven habits for personal and interpersonal effectiveness, focusing on principles of character and collaboration."));
        bookList.add(new Book("The Subtle Art of Not Giving a F*ck", "Mark Manson", "Self Help", 9.99,R.drawable.thesubtle, "A counterintuitive approach to happiness and success, advocating for embracing life's uncertainties and focusing on what truly matters."));

        return bookList;
    }
    void gotocart(){
        Intent intent = new Intent(this,CartActivity.class);
        startActivity(intent);
        finish();
    }








}
