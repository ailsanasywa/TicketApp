package com.example.ticketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                var ticketPrice by rememberSaveable {
                    mutableIntStateOf(75000)
                }

                var ticketAmount by rememberSaveable {
                    mutableIntStateOf(1)
                }

                var buyerName by rememberSaveable {
                    mutableStateOf("")
                }

                var orderStatus by rememberSaveable {
                    mutableStateOf("IDLE")
                }

                LaunchedEffect(orderStatus) {

                    if (orderStatus == "PROCESSING") {

                        delay(5000)

                        orderStatus = "SUCCESS"
                    }
                }

                Scaffold { innerPadding ->

                    TicketScreen(

                        modifier = Modifier.padding(innerPadding),

                        ticketPrice = ticketPrice,

                        ticketAmount = ticketAmount,

                        buyerName = buyerName,

                        orderStatus = orderStatus,

                        onNameChange = {

                            buyerName = it

                            if (
                                orderStatus == "EMPTY_NAME" &&
                                it.isNotBlank()
                            ) {
                                orderStatus = "IDLE"
                            }
                        },

                        onIncrease = {

                            ticketAmount++
                        },

                        onDecrease = {

                            if (ticketAmount > 1) {
                                ticketAmount--
                            }
                        },

                        onOrderClick = {

                            if (buyerName.isBlank()) {

                                orderStatus = "EMPTY_NAME"

                            } else {

                                orderStatus = "PROCESSING"
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TicketScreen(

    modifier: Modifier = Modifier,

    ticketPrice: Int,
    ticketAmount: Int,
    buyerName: String,
    orderStatus: String,

    onNameChange: (String) -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onOrderClick: () -> Unit
) {

    val backgroundColor = Color(0xFF171A21)

    val ticketColor = Color(0xFFFFF8ED)

    val accentColor = Color(0xFFE17A47)

    val darkText = Color(0xFF242424)


    Box(

        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp)

    ) {

        Column {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector =
                        Icons.Default.ConfirmationNumber,

                    contentDescription =
                        "Ticket Icon",

                    tint = accentColor,

                    modifier = Modifier.size(32.dp)
                )


                Spacer(
                    modifier = Modifier.size(10.dp)
                )


                Text(

                    text = "TICKET",

                    color = Color.White,

                    fontSize = 30.sp,

                    fontWeight = FontWeight.Bold
                )
            }

            Text(

                text =
                    "Pesan tiketmu dengan mudah.",

                color = Color(0xFFB8BBC5),

                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(

                    containerColor = ticketColor
                )

            ) {

                Column(

                    modifier =
                        Modifier.padding(22.dp)

                ) {

                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.ConfirmationNumber,

                            contentDescription = null,

                            tint = accentColor,

                            modifier =
                                Modifier.size(18.dp)
                        )


                        Spacer(
                            modifier = Modifier.size(6.dp)
                        )


                        Text(

                            text = "EVENT PASS",

                            color = accentColor,

                            fontSize = 13.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(

                        text =
                            "General Admission",

                        color = darkText,

                        fontSize = 24.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(

                        text =
                            "${formatRupiah(ticketPrice)} / tiket",

                        color = Color.Gray,

                        fontSize = 15.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    Text(

                        text = "Nama Pembeli",

                        color = darkText,

                        fontWeight =
                            FontWeight.SemiBold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    OutlinedTextField(

                        value = buyerName,

                        onValueChange =
                            onNameChange,

                        modifier =
                            Modifier.fillMaxWidth(),

                        placeholder = {

                            Text(
                                text =
                                    "Masukkan nama Anda"
                            )
                        },

                        leadingIcon = {

                            Icon(

                                imageVector =
                                    Icons.Default.Person,

                                contentDescription =
                                    "Nama Pembeli",

                                tint =
                                    accentColor
                            )
                        },

                        keyboardOptions =
                            KeyboardOptions(

                                capitalization =
                                    KeyboardCapitalization.Words
                            ),

                        singleLine = true
                    )

                    Spacer(
                        modifier =
                            Modifier.height(22.dp)
                    )

                    Text(

                        text = "Jumlah Tiket",

                        color = darkText,

                        fontWeight =
                            FontWeight.SemiBold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        Button(
                            onClick = onDecrease,
                            modifier = Modifier.size(
                                width = 62.dp,
                                height = 52.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF1E8DC),
                                contentColor = darkText
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Kurangi tiket",
                                modifier = Modifier.size(70.dp)
                            )
                        }

                        Column(

                            horizontalAlignment =
                                Alignment.CenterHorizontally

                        ) {

                            Text(

                                text =
                                    "$ticketAmount",

                                color =
                                    darkText,

                                fontSize =
                                    28.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )

                            Text(

                                text = "tiket",

                                color =
                                    Color.Gray,

                                fontSize =
                                    12.sp
                            )
                        }

                        Button(
                            onClick = onIncrease,
                            modifier = Modifier.size(
                                width = 62.dp,
                                height = 52.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accentColor,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Tambah tiket",
                                modifier = Modifier.size(70.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        Text(

                            text = "Total",

                            color =
                                Color.Gray,

                            fontSize =
                                15.sp
                        )

                        Text(

                            text =
                                formatRupiah(
                                    ticketPrice *
                                            ticketAmount
                                ),

                            color =
                                darkText,

                            fontSize =
                                20.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(22.dp)
                    )

                    Button(

                        onClick =
                            onOrderClick,

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(54.dp),

                        enabled =
                            orderStatus !=
                                    "PROCESSING",

                        shape =
                            RoundedCornerShape(
                                14.dp
                            ),

                        colors =
                            ButtonDefaults
                                .buttonColors(

                                    containerColor =
                                        accentColor,

                                    contentColor =
                                        Color.White
                                )

                    ) {

                        Icon(

                            imageVector =
                                if (
                                    orderStatus ==
                                    "PROCESSING"
                                ) {

                                    Icons.Default
                                        .HourglassTop

                                } else {

                                    Icons.Default
                                        .ConfirmationNumber
                                },

                            contentDescription =
                                null
                        )

                        Spacer(
                            modifier =
                                Modifier.size(8.dp)
                        )

                        Text(

                            text =
                                if (
                                    orderStatus ==
                                    "PROCESSING"
                                ) {

                                    "Memproses..."

                                } else {

                                    "Pesan Tiket"
                                },

                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            StatusCard(
                status = orderStatus
            )
        }
    }
}

@Composable
fun StatusCard(

    status: String

) {

    val message: String

    val background: Color

    val textColor: Color

    val icon = when (status) {

        "EMPTY_NAME" -> {

            Icons.Default.Error
        }

        "PROCESSING" -> {

            Icons.Default.HourglassTop
        }

        "SUCCESS" -> {

            Icons.Default.CheckCircle
        }

        else -> {

            Icons.Default.ConfirmationNumber
        }
    }

    when (status) {

        "EMPTY_NAME" -> {

            message =
                "Nama masih kosong"

            background =
                Color(0xFFFFE6E6)

            textColor =
                Color(0xFFB3261E)
        }

        "PROCESSING" -> {

            message =
                "Memproses pesanan..."

            background =
                Color(0xFFFFF0D9)

            textColor =
                Color(0xFFA55A00)
        }

        "SUCCESS" -> {

            message =
                "Tiket telah dipesan"

            background =
                Color(0xFFE1F5E7)

            textColor =
                Color(0xFF20733B)
        }

        else -> {

            message =
                "Silakan isi data pemesanan"

            background =
                Color(0xFFE9E9EC)

            textColor =
                Color(0xFF53545A)
        }
    }

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(16.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    background
            )

    ) {

        Row(

            modifier =
                Modifier.padding(

                    horizontal = 18.dp,

                    vertical = 16.dp
                ),

            verticalAlignment =
                Alignment.CenterVertically

        ) {

            Icon(

                imageVector = icon,

                contentDescription = null,

                tint = textColor
            )

            Spacer(
                modifier =
                    Modifier.size(10.dp)
            )

            Text(

                text =
                    "Status: $message",

                color =
                    textColor,

                fontSize =
                    14.sp,

                fontWeight =
                    FontWeight.SemiBold
            )
        }
    }
}

fun formatRupiah(
    value: Int
): String {

    val formatter =
        NumberFormat.getCurrencyInstance(
            Locale("id", "ID")
        )

    formatter.maximumFractionDigits = 0

    return formatter.format(value)
}