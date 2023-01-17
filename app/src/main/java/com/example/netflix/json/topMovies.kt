package com.example.netflix.json


val jsonTop = """
{
  "movies": [
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FAvtar.mkv?alt=media&token=5398d132-304e-4552-a554-47674a9225a0",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Favatar.jpg?alt=media&token=9da3f9a9-ff2c-4469-aee1-d2ace990e84c",
      "movieName": "Avatar",
      "categories": ["Adventure", "Fantasy"],
      "length": "172"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FJumanji2.mkv?alt=media&token=8bde7851-663c-40e6-8f32-1d945d77ac6c",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fjumanji.jpg?alt=media&token=2e9e0931-32e4-464d-af2b-27599ef53c18",
      "movieName": "Jumanji 2",
      "categories": ["Adventure", "Comedy"],
      "length": "162"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FRedNotice.mkv?alt=media&token=d7633f04-0bd7-4cdc-b12f-3e1479dce076",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FRedNotice.jpg?alt=media&token=af172d11-0bae-41aa-9246-ed65bf7d3aa6",
      "movieName": "Red Notice",
      "categories": ["Adventure", "Comedy"],
      "length": "172"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FHobbsAndShows.mkv?alt=media&token=3e94b7ef-fff7-4641-a3f6-158358fccb47",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FhobsAndShows.jpg?alt=media&token=1a81db64-dbac-4a08-a2ac-67e348568928",
      "movieName": "Hobbs And Shows",
      "categories": ["Adventure", "Action"],
      "length": "156"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FWarcraft.mkv?alt=media&token=60e76791-b0fa-4851-82d6-1e0963ced7ef",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fwarcraft.jpg?alt=media&token=33e17a11-2ad5-4787-95b2-df5f6ab75501",
      "movieName": "Warcraft",
      "categories": ["Adventure", "Fantasy"],
      "length": "148"
    },
   {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FTadka.mkv?alt=media&token=1c05e5f0-a018-4011-831c-20b310dae48d",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Ftadka.jpg?alt=media&token=6843780e-6655-4db8-99fa-4539f846b627",
      "movieName": "Tadka",
      "categories": ["Romance", "Comedy"],
      "length": "137"
    },
    
    
     
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FQala.mkv?alt=media&token=ff3e2490-b843-4f4e-b367-4ebb12713326",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FQala.jpg?alt=media&token=1e585a99-bcb4-4e52-a8ff-c6a728b80c0e",
      "movieName": "Qala",
      "categories": ["Musical", "Drama"],
      "length": "150"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FBlurr.mkv?alt=media&token=108edcae-cec6-493e-9bb7-9faddb1d479d",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fblurr.jpg?alt=media&token=b736745f-d048-4f28-b756-6a0a85780030",
      "movieName": "Blurr",
       "categories": ["Horror", "Thriller"],
      "length": "125"
    },
   
  ]
}
"""

fun getTopMovieJsonData(): String {
    return jsonTop
}


