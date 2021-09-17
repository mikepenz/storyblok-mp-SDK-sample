package components

import com.mikepenz.storyblok.sdk.model.Story
import components.materialui.ListItem
import components.materialui.ListItemText
import react.RBuilder

fun RBuilder.StoryList(
    story: List<Story>,
    selectedStory: Story?,
    onSelect: (Story) -> Unit
) {
    components.materialui.List {
        story.forEach { item ->
            ListItem {
                attrs {
                    button = true
                    key = item.name ?: ""
                    selected = item == selectedStory
                    onClick = {
                        onSelect(item)
                    }
                }
                ListItemText {
                    +"${item.name}"
                }
            }
        }
    }
}