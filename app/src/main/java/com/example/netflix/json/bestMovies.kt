package com.example.netflix.json


val jsonBest = """
{
  "movies": [
   
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FGoodBye.mkv?alt=media&token=06573c7f-b76b-4788-9c27-44347e95b464",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FGoodBye.jpg?alt=media&token=dfa80f6f-8fa9-4237-bfaf-e0cb9eb4eed0",
      "movieName": "Good Bye",
      "categories": ["Family", "Drama"],
      "length": "142"
    },
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FTadka.mkv?alt=media&token=1c05e5f0-a018-4011-831c-20b310dae48d",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Ftadka.jpg?alt=media&token=6843780e-6655-4db8-99fa-4539f846b627",
      "movieName": "Tadka",
      "categories": ["Romance", "Comedy"],
      "length": "137"
    },
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FBlurr.mkv?alt=media&token=108edcae-cec6-493e-9bb7-9faddb1d479d",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fblurr.jpg?alt=media&token=b736745f-d048-4f28-b756-6a0a85780030",
      "movieName": "Blurr",
       "categories": ["Horror", "Thriller"],
      "length": "125"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FQala.mkv?alt=media&token=ff3e2490-b843-4f4e-b367-4ebb12713326",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FQala.jpg?alt=media&token=1e585a99-bcb4-4e52-a8ff-c6a728b80c0e",
      "movieName": "Qala",
      "categories": ["Musical", "Drama"],
      "length": "150"
    },
       {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FKhuda%20Haafiz%20.mkv?alt=media&token=7937a70f-f536-4da0-8de2-17f150c76156",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FKhudaHaafiz.jpg?alt=media&token=c416a9a2-7f94-425f-a176-8da5bb00991d",
      "movieName": "Khuda Haafiz",
      "categories": ["Action", "Adventure"],
      "length": "120"
    },
    
  ]
}
"""

fun getBestMovieJsonData(): String {
    return jsonBest
}
