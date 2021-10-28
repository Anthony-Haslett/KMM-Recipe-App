package com.haslett.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.haslett.food2forkkmm.domain.model.GenericMessageInfo
import com.haslett.food2forkkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadMessageFromQueue: () -> Unit,
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            title = dialogInfo.title,
            description = dialogInfo.description,
            onRemoveHeadFromQueue = onRemoveHeadMessageFromQueue,
        )
    }
}
