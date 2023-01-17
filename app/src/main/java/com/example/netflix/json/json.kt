package com.example.netflix.json


val jsonData = """
{
  "movies": [
    {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FKhuda%20Haafiz%20.mkv?alt=media&token=7937a70f-f536-4da0-8de2-17f150c76156",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FKhudaHaafiz.jpg?alt=media&token=c416a9a2-7f94-425f-a176-8da5bb00991d",
      "movieName": "Khuda Haafiz",
      "categories": ["Action", "Adventure"],
      "length": "120"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2Fdobaaraa.mkv?alt=media&token=7dfa1dd2-cab5-4de4-a802-8d9a7d70a26b",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FDoBaaraa.jpg?alt=media&token=ada7ad42-9dc6-4e95-8424-8ed57104be7f",
      "movieName": "Do Baaraa",
      "categories": ["Drama", "Thriller"],
      "length": "130"
    },
     {
      "movieUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FMonicaOMyDarling%20.mkv?alt=media&token=4ba6081e-721a-468b-b340-0439429d3125",
      "imageUrl": "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2FMOMD.jpg?alt=media&token=e7ef98b5-a8ca-4fa6-96ec-7ba6196d4f27",
      "movieName": "Monica Oh My Darling",
      "categories": ["Comedy", "Mystery"],
      "length": "145"
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
  ]
}
"""

fun getMovieJsonData(): String {
 return jsonData
}


