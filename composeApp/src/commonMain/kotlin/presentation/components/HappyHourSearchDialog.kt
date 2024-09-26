package presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import domain.Validator
import domain.model.OutOfRangeException
import domain.model.SearchParameter
import domain.model.SearchType
import kotlinx.datetime.LocalDate
import network.chaintech.ui.datepicker.WheelDatePickerDialog
import network.chaintech.utils.now

@Composable
fun HappyHourSearchDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickSearch: (SearchParameter) -> Unit
) {
    val tabs = listOf(
        SearchType.BY_PART,
        SearchType.BY_DATE,
        SearchType.BY_TEXT,
    )

    var selectedType by remember {
        mutableStateOf(tabs[0])
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = "Search",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )

                        }
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = {
                                onDismissRequest()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    }
                    SearchTypeRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            .padding(8.dp),
                        selected = selectedType == SearchType.BY_PART,
                        label = "By Part",
                        onClick = {
                            selectedType = SearchType.BY_PART
                        },
                    )
                    SearchTypeRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            .padding(8.dp),
                        selected = selectedType == SearchType.BY_DATE,
                        label = "By Date",
                        onClick = {
                            selectedType = SearchType.BY_DATE
                        }
                    )
                    SearchTypeRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            .padding(top = 8.dp),
                        selected = selectedType == SearchType.BY_TEXT,
                        label = "By Text",
                        onClick = {
                            selectedType = SearchType.BY_TEXT
                        }
                    )
                    Divider(modifier = Modifier.fillMaxWidth(.9f))
                    when (selectedType) {
                        SearchType.BY_DATE -> {
                            SearchByDateCard(
                                modifier = Modifier.padding(16.dp),
                                latestHappyHourDate = LocalDate.now(),
                                oldestHappyHourDate = LocalDate.parse("2017-04-17"),
                                onClick = { date ->
                                    onClickSearch(date)
                                }
                            )
                        }

                        SearchType.BY_PART -> {
                            SearchByPartCard(
                                modifier = Modifier.padding(16.dp),
                                latestPartNumber = 2000,
                                onClick = { part ->
                                    onClickSearch(part)
                                }
                            )
                        }

                        SearchType.BY_TEXT -> {
                            SearchByTextCard(
                                modifier = Modifier.padding(16.dp),
                                onClick = { searchedText ->
                                    onClickSearch(searchedText)
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SearchTypeRow(
    modifier: Modifier = Modifier,
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = if (selected) {
            MaterialTheme.colors.primaryVariant
        } else {
            MaterialTheme.colors.surface
        },
        shape = MaterialTheme.shapes.medium,
        border = if (selected) {
            BorderStroke(1.dp, MaterialTheme.colors.primary)
        } else {
            null
        },
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier.weight(1f),
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colors.primary
                )
            )
            Text(
                modifier = Modifier.weight(3f),
                text = label,
            )
        }
    }
}

@Composable
private fun SearchByPartCard(
    modifier: Modifier = Modifier,
    latestPartNumber: Int,
    onClick: (SearchParameter.PartNumberSearchParameter) -> Unit
) {
    val validRange by remember(latestPartNumber) {
        derivedStateOf {
            0..latestPartNumber
        }
    }
    var partString by remember {
        mutableStateOf("")
    }

    val part by remember(partString) {
        derivedStateOf {
            Validator.isPartStringValid(partString, validRange)
        }
    }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier,
                value = partString,
                onValueChange = {
                    partString = it
                },
                isError = !part.isValid && partString.isNotEmpty(),
                label = {
                    Text("Part")
                },
                placeholder = {
                    Text(
                        text = "pl.: $latestPartNumber",
                        fontStyle = FontStyle.Italic,
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = MaterialTheme.colors.primary,
                    errorCursorColor = MaterialTheme.colors.error,
                    leadingIconColor = MaterialTheme.colors.primary,
                    errorLeadingIconColor = MaterialTheme.colors.error,
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    focusedLabelColor = MaterialTheme.colors.primary,
                    trailingIconColor = MaterialTheme.colors.error,
                    errorTrailingIconColor = MaterialTheme.colors.error,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Pin,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(!part.isValid && partString.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                        )
                    }
                }
            )
            AnimatedVisibility(!part.isValid && partString.isNotEmpty()) {
                if (!part.isValid) {
                    when (part.exception) {
                        is NumberFormatException -> Text("Please enter  a valid number!")
                        is OutOfRangeException -> Text("Please enter a number with in ${validRange}!")
                        else -> Text("Something went wrong pleas, try again!")
                    }
                }
            }
            SearchButton(
                modifier = Modifier.fillMaxWidth(.7f).padding(16.dp),
                enabled = part.isValid,
                onClick = {
                    onClick(
                        SearchParameter.PartNumberSearchParameter(part.t!!)
                    )
                }
            )
        }
    }
}

@Composable
private fun SearchByDateCard(
    modifier: Modifier = Modifier,
    latestHappyHourDate: LocalDate,
    oldestHappyHourDate: LocalDate,
    onClick: (SearchParameter.DateSearchParameter) -> Unit
) {
    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var wheelDatePickerShows by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(wheelDatePickerShows) {
        WheelDatePickerDialog(
            modifier = Modifier.padding(16.dp),
            showDatePicker = wheelDatePickerShows,
            title = "Select Date",
            doneLabelStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.primary
            ),
            onDismiss = {
                wheelDatePickerShows = false
            },
            onDoneClick = { date ->
                selectedDate = date
                wheelDatePickerShows = false
            },
            height = 200.dp,
            maxDate = latestHappyHourDate,
            minDate = oldestHappyHourDate,
            dateTextColor = MaterialTheme.colors.primary,
            containerColor = MaterialTheme.colors.surface
        )
    }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedButton(
                modifier = Modifier
                    .height(TextFieldDefaults.MinHeight)
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                onClick = {
                    wheelDatePickerShows = true
                },
                border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colors.primary,
                )
            ) {
                Text(selectedDate.toString())
            }
            SearchButton(
                modifier = Modifier.fillMaxWidth(.7f).padding(16.dp),
                enabled = true,
                onClick = {
                    onClick(
                        SearchParameter.DateSearchParameter(selectedDate)
                    )
                }
            )
        }
    }
}

@Composable
private fun SearchByTextCard(
    modifier: Modifier = Modifier,
    onClick: (SearchParameter.TextSearchParameter) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    val isSearchedTextValid by remember(text) {
        derivedStateOf {
            text.isNotBlank()
        }
    }


    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = {
                    Text("Searched text")
                },
                placeholder = {
                    Text(
                        text = "pl.: Orgonit",
                        fontStyle = FontStyle.Italic,
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = MaterialTheme.colors.primary,
                    errorCursorColor = MaterialTheme.colors.error,
                    leadingIconColor = MaterialTheme.colors.primary,
                    errorLeadingIconColor = MaterialTheme.colors.error,
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    focusedLabelColor = MaterialTheme.colors.primary,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Abc,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(text.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                text = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                            )
                        }
                    }
                }
            )

            SearchButton(
                modifier = Modifier.fillMaxWidth(.7f).padding(16.dp),
                enabled = isSearchedTextValid,
                onClick = {
                    onClick(
                        SearchParameter.TextSearchParameter(text)
                    )
                }
            )
        }
    }
}

@Composable
private fun SearchButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
        Text(
            "Search"
        )
    }
}