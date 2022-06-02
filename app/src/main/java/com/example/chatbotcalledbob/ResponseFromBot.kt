package com.example.chatbotcalledbob

object ResponseFromBot{
    fun allResponses(_message: String): String{
        val random = (0..1).random()
        val message = _message.lowercase()

        return when {
            message.contains("hello") -> {
                when (random){
                    0 -> "Sup"
                    1 -> "Helloo!"
                    else -> "error"
                }
            }

            message.contains("delivery options") -> {
                when (random){
                    0 -> "You can pick what delivery option you want when you're paying"
                    1 -> "Ypu can pick up in the store, get the package sent home, express delivery or pick it up by using InstaBox"
                    else -> "error"
                }
            }

            message.contains("return products") -> {
                when (random){
                    0 -> "It costs you 35 kr to send your package back"
                    1 -> "If youre a member its free, and you can return it in a store for free"
                    else -> "error"
                }
            }

            else -> {
                when (random){
                    0 -> "Idk"
                    1 -> "Try asking me again please"
                    else -> "error"
                }
            }
        }

    }
}
