package com.example.karmika.feature.customer.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomerNotificationScreen(
    onBack: () -> Unit
) {

    // =====================================================
    // TEMPORARY NOTIFICATIONS
    // =====================================================

    val notifications = listOf(

        CustomerNotification(
            title = "Booking Confirmed",
            message = "Your plumbing service has been confirmed.",
            time = "10 min ago",
            type = NotificationType.BOOKING
        ),

        CustomerNotification(
            title = "Service Reminder",
            message = "Your service is scheduled for tomorrow at 10:00 AM.",
            time = "2 hours ago",
            type = NotificationType.REMINDER
        ),

        CustomerNotification(
            title = "Booking Completed",
            message = "Your electrical service has been completed.",
            time = "Yesterday",
            type = NotificationType.SUCCESS
        ),

        CustomerNotification(
            title = "Booking Cancelled",
            message = "Your cleaning service booking was cancelled.",
            time = "2 days ago",
            type = NotificationType.CANCELLED
        )
    )


    // =====================================================
    // SCREEN
    // =====================================================

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // =================================================
        // HEADER
        // =================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector =
                        Icons.Outlined.ArrowBack,

                    contentDescription = "Back"
                )
            }


            Text(
                text = "Notifications",

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold
            )
        }


        // =================================================
        // NOTIFICATION LIST
        // =================================================

        if (notifications.isEmpty()) {

            EmptyNotifications()

        } else {

            LazyColumn(

                modifier = Modifier.fillMaxSize(),

                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(notifications) { notification ->

                    NotificationItem(
                        notification = notification,
                        onClick = {
                            // Notification action later
                        }
                    )
                }
            }
        }
    }
}


// =========================================================
// NOTIFICATION DATA
// =========================================================

private data class CustomerNotification(
    val title: String,
    val message: String,
    val time: String,
    val type: NotificationType
)


// =========================================================
// NOTIFICATION TYPE
// =========================================================

private enum class NotificationType {

    BOOKING,
    REMINDER,
    SUCCESS,
    CANCELLED
}


// =========================================================
// NOTIFICATION ITEM
// =========================================================

@Composable
private fun NotificationItem(
    notification: CustomerNotification,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = androidx.compose.foundation.shape
            .RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment =
                Alignment.Top
        ) {

            // =============================================
            // ICON
            // =============================================

            NotificationIcon(
                type = notification.type
            )


            Spacer(
                modifier = Modifier.size(14.dp)
            )


            // =============================================
            // CONTENT
            // =============================================

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = notification.title,

                    fontSize = 16.sp,

                    fontWeight =
                        FontWeight.SemiBold
                )


                Spacer(
                    modifier = Modifier.size(5.dp)
                )


                Text(
                    text = notification.message,

                    fontSize = 14.sp,

                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )


                Spacer(
                    modifier = Modifier.size(7.dp)
                )


                Text(
                    text = notification.time,

                    fontSize = 12.sp,

                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )
            }
        }
    }
}


// =========================================================
// NOTIFICATION ICON
// =========================================================

@Composable
private fun NotificationIcon(
    type: NotificationType
) {

    val icon = when (type) {

        NotificationType.BOOKING ->
            Icons.Outlined.CalendarMonth

        NotificationType.REMINDER ->
            Icons.Outlined.NotificationsNone

        NotificationType.SUCCESS ->
            Icons.Outlined.CheckCircle

        NotificationType.CANCELLED ->
            Icons.Outlined.Close
    }


    Icon(
        imageVector = icon,

        contentDescription = null,

        modifier = Modifier.size(28.dp),

        tint = MaterialTheme.colorScheme.primary
    )
}


// =========================================================
// EMPTY NOTIFICATIONS
// =========================================================

@Composable
private fun EmptyNotifications() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Icon(
            imageVector =
                Icons.Outlined.NotificationsNone,

            contentDescription = null,

            modifier = Modifier.size(56.dp),

            tint =
                MaterialTheme.colorScheme
                    .onSurfaceVariant
        )


        Spacer(
            modifier = Modifier.size(16.dp)
        )


        Text(
            text = "No notifications",

            fontSize = 18.sp,

            fontWeight = FontWeight.SemiBold
        )


        Spacer(
            modifier = Modifier.size(6.dp)
        )


        Text(
            text = "You're all caught up.",

            fontSize = 14.sp,

            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant
        )
    }
}