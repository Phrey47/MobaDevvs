package com.bibleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bibleapp.ui.theme.Amber600
import com.bibleapp.viewmodel.BibleViewModel

@Composable
fun SettingsScreen(vm: BibleViewModel) {
    val settings by vm.settings.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Surface(color = MaterialTheme.colorScheme.surface) {
                Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 52.dp, bottom = 16.dp)) {
                    Text("Settings", fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
            HorizontalDivider()
        }

        item {
            SectionHeader("Reading")
            SettingsCard {
                // Theme
                SettingRow(icon = Icons.Outlined.DarkMode, title = "Theme") {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("light", "dark", "sepia").forEach { t ->
                            val sel = settings.theme == t
                            Button(
                                onClick = { vm.updateSettings { copy(theme = t) } },
                                colors = if (sel) ButtonDefaults.buttonColors(containerColor = Amber600)
                                else ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                modifier = Modifier.height(34.dp)
                            ) { Text(t.replaceFirstChar { it.uppercase() }, fontSize = 12.sp) }
                        }
                    }
                }
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                // Font size
                SettingRow(icon = Icons.Outlined.TextFields, title = "Font Size") {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("sm" to "S", "md" to "M", "lg" to "L", "xl" to "XL").forEach { (key, label) ->
                            val sel = settings.fontSize == key
                            Button(
                                onClick = { vm.updateSettings { copy(fontSize = key) } },
                                colors = if (sel) ButtonDefaults.buttonColors(containerColor = Amber600)
                                else ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                modifier = Modifier.height(34.dp)
                            ) { Text(label, fontSize = 12.sp) }
                        }
                    }
                }
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                // Translation
                SettingRow(icon = Icons.Outlined.Translate, title = "Translation") {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("KJV", "NIV", "ESV", "NLT").forEach { t ->
                            val sel = settings.translation == t
                            Button(
                                onClick = { vm.updateSettings { copy(translation = t) } },
                                colors = if (sel) ButtonDefaults.buttonColors(containerColor = Amber600)
                                else ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                modifier = Modifier.height(34.dp)
                            ) { Text(t, fontSize = 12.sp) }
                        }
                    }
                }
            }
        }

        item {
            SectionHeader("About")
            SettingsCard {
                SettingRowNav(icon = Icons.Outlined.Info, title = "About Bible App", onClick = {})
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                SettingRowNav(icon = Icons.Outlined.Favorite, title = "Rate the App", onClick = {})
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                SettingRowNav(icon = Icons.Outlined.Share, title = "Share with Friends", onClick = {})
            }
        }

        item {
            Spacer(Modifier.height(32.dp))
            Text("Bible App v1.0", modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(title.uppercase(), fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp))
}

@Composable
private fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp),
        border = CardDefaults.outlinedCardBorder()
    ) { Column(content = content) }
}

@Composable
private fun SettingRow(icon: ImageVector, title: String, content: @Composable () -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 12.dp)) {
            Box(Modifier.size(32.dp).background(MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                Icon(icon, null, modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)
        }
        content()
    }
}

@Composable
private fun SettingRowNav(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(Modifier.size(32.dp).background(MaterialTheme.colorScheme.surfaceVariant,
            RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
            Icon(icon, null, modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(title, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f))
        Icon(Icons.Outlined.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp))
    }
}
