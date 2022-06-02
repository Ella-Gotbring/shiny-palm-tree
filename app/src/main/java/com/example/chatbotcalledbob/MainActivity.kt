package com.example.chatbotcalledbob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import com.example.chatbotcalledbob.Constants.RECIEVE_ID
import com.example.chatbotcalledbob.Constants.SEND_ID
import com.example.chatbotcalledbob.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessageAdapter
    private val botList = ("Bob")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView()
        clickEvents()

        //message from bot
        customMessage("Hello! I am the store's ChatBot called ${botList}. I would love to help you. During our opening hours, there is the opportunity to talk to a colleague in the live chat if I can't help you.\n" +
                "\n" + "Feel free to summarize your question in a short sentence ")
    }
    private fun clickEvents(){
        //send
        binding.btnSend.setOnClickListener{
            sendMessage()
        }

    }

    private fun recyclerView(){
        adapter = MessageAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun customMessage(message: String){
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main){
                messagesList.add(Message(message, RECIEVE_ID))
                adapter.insertMessage(Message(message, RECIEVE_ID))

                //updating ui to latest message
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }

    }

    private fun sendMessage(){
        val message = binding.etMessage.text.toString()
        if(message.isNotEmpty()) {
            binding.etMessage.setText("")
            adapter.insertMessage(Message(message, SEND_ID))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            //take the message we want to send and put it to message to get a response back
            ResponseFromBot(message)

        }

    }

    private fun ResponseFromBot(message: String){
        val response = ResponseFromBot.allResponses(message)
        //insert message to adapter
        adapter.insertMessage(Message(response, RECIEVE_ID))
        binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

    }

//reopens the app -> bottom scroll where the latest messages are
    override fun onStart() {
    super.onStart()
    GlobalScope.launch {
        delay(100)
        withContext(Dispatchers.Main) {
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
        }
    }


    }


}