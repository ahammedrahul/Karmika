package com.example.karmika.feature.customer.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomerNotificationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onMarkAllRead: () -> Unit = {}
) {
    val screenBg = Color(0xFFF7FBF9)

    // =====================================================
    // SAMPLE NOTIFICATIONS DATA
    // =====================================================
    val notifications = listOf(
        CustomerNotification(
            id = "1",
            title = "Booking Confirmed",
            message = "Your plumbing service has been confirmed for tomorrow.",
            time = "10 min ago",
            type = NotificationType.BOOKING,
            isRead = false
        ),
        CustomerNotification(
            id = "2",
            title = "Service Reminder",
            message = "Your home cleaning service is scheduled for tomorrow at 10:00 AM.",
            time = "2 hours ago",
            type = NotificationType.REMINDER,
            isRead = false
        ),
        CustomerNotification(
            id = "3",
            title = "Booking Completed",
            message = "Your electrical maintenance service has been completed successfully.",
            time = "Yesterday",
            type = NotificationType.SUCCESS,
            isRead = true
        ),
        CustomerNotification(
            id = "4",
            title = "Booking Cancelled",
            message = "Your pest control service booking was cancelled as requested.",
            time = "2 days ago",
            type = NotificationType.CANCELLED,
            isRead = true
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenBg)
    ) {

        // =================================================
        // HEADER
        // =================================================
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF0F172A)
                )
            }

            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = Color(0xFF0F172A),
                modifier = Modifier.weight(1f)
            )

            if (notifications.any { !it.isRead }) {
                TextButton(onClick = onMarkAllRead) {
                    Text(
                        text = "Mark as read",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color(0xFF0D6338)
                    )
                }
            }
        }

        // =================================================
        // NOTIFICATION LIST CONTENT
        // =================================================
        if (notifications.isEmpty()) {
            EmptyNotifications()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notifications, key = { it.id }) { notification ->
                    NotificationItem(
                        notification = notification,
                        onClick = {
                            // Handle click navigation/action
                        }
                    )
                }
            }
        }
    }
}

// =========================================================
// DATA MODELS
// =========================================================
private data class CustomerNotification(
    val id: String,
    val title: String,
    val message: String,
    val time: String,
    val type: NotificationType,
    val isRead: Boolean
)

private enum class NotificationType(
    val icon: ImageVector,
    val iconTint: Color,
    val badgeBgColor: Color
) {
    BOOKING(
        icon = Icons.Rounded.CalendarToday,
        iconTint = Color(0xFF2563EB),       // Deep Blue
        badgeBgColor = Color(0xFFDBEAFE)    // Soft Blue
    ),
    REMINDER(
        icon = Icons.Rounded.Notifications,
        iconTint = Color(0xFFD97706),       // Warm Amber
        badgeBgColor = Color(0xFFFEF3C7)    // Soft Amber
    ),
    SUCCESS(
        icon = Icons.Rounded.CheckCircle,
        iconTint = Color(0xFF059669),       // Emerald Green
        badgeBgColor = Color(0xFFD1FAE5)    // Soft Green
    ),
    CANCELLED(
        icon = Icons.Rounded.Cancel,
        iconTint = Color(0xFFDC2626),       // Crimson Red
        badgeBgColor = Color(0xFFFEE2E2)    // Soft Red
    )
}

// =========================================================
// NOTIFICATION ITEM CARD
// =========================================================
@Composable
private fun NotificationItem(
    notification: CustomerNotification,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = if (notification.isRead) Color.White else Color(0xFFF0FDF4),
        shadowElevation = if (notification.isRead) 0.5.dp else 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            // Type Icon with customized Background Badge
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(notification.type.badgeBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = notification.type.icon,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = notification.type.iconTint
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Notification Details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (!notification.isRead) FontWeight.Bold else FontWeight.SemiBold,
                            fontSize = 15.sp
                        ),
                        color = Color(0xFF0F172A),
                        modifier = Modifier.weight(1f)
                    )

                    // Unread Dot Indicator
                    if (!notification.isRead) {
                        Box(
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0D6338))
                        )
                    }
                }

                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    color = Color(0xFF475569),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = notification.time,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    color = Color(0xFF94A3B8)
                )
            }
        }
    }
}

// =========================================================
// EMPTY STATE
// =========================================================
@Composable
private fun EmptyNotifications() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFEAF5EF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.NotificationsNone,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF0D6338)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "No Notifications",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            color = Color(0xFF0F172A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "You're all caught up! Check back later for updates on your bookings.",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
            color = Color(0xFF64748B)
        )
    }
}