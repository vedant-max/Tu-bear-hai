package co.ssup.tuBearHai.data.response

data class Beer(
  val id: Int,
  val name: String,
  val tagline: String,
  val first_brewed: String,
  val description: String,
  val image_url: String,
  val ingredients: Ingredients,
  val food_pairing: List<String>,
  val brewers_tips: String,
)

data class Ingredients(
  val malt: List<Malt>,
  val hops: List<Hop>,
  val yeast: String,
)

data class Malt(
  val name: String,
  val amount: Amount,
)

data class Hop(
  val name: String,
  val amount: Amount,
  val add: String,
  val attribute: String,
)

data class Amount(
  val value: Double,
  val unit: String,
)
