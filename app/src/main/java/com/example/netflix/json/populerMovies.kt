package com.example.netflix.json


val jsonPopuler = """
{
  "movies": [
   
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FChup.mkv?alt=media&token=93cd85bc-7ad7-4ee9-addc-a6d6af3c36a8",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fchup.jpg?alt=media&token=bc6c8617-2432-49bf-8d4a-e36644192bf7",
      "movieName": "Chup",
       "categories": ["Mystery", "Thriller"],
      "length": "115"
    },
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FFreddy.mkv?alt=media&token=33efe9e2-4495-4ddc-9f8b-6e4ac81e4bd9",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Ffreddy.jpg?alt=media&token=f2637618-3ed1-4f09-b8d6-1137c9c40293",
      "movieName": "Freddy",
      "categories": ["Mystery", "Thriller"],
      "length": "135"
    },
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FHit.mkv?alt=media&token=776e7c6f-7486-4ed7-9f95-c0b6cc56e2eb",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fhit.jpg?alt=media&token=fe73ff64-6807-4816-83b6-cbc5f2c2301a",
      "movieName": "Hit",
      "categories": ["Action", "Adventure"],
      "length": "123"
    }, {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FTadka.mkv?alt=media&token=1c05e5f0-a018-4011-831c-20b310dae48d",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Ftadka.jpg?alt=media&token=6843780e-6655-4db8-99fa-4539f846b627",
      "movieName": "Tadka",
      "categories": ["Romance", "Comedy"],
      "length": "137"
    },
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FGoodBye.mkv?alt=media&token=06573c7f-b76b-4788-9c27-44347e95b464",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FGoodBye.jpg?alt=media&token=dfa80f6f-8fa9-4237-bfaf-e0cb9eb4eed0",
      "movieName": "Good Bye",
      "categories": ["Family", "Drama"],
      "length": "142"
    },
  ]
}
"""

fun getPopulerMovieJsonData(): String {
    return jsonPopuler
}
