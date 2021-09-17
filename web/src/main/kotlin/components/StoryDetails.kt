package components

import com.mikepenz.storyblok.sdk.model.Story
import kotlinx.css.Align
import kotlinx.css.alignSelf
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.StoryDetails(story: Story) {
    styledDiv {
        css {
            padding(32.px)
        }

        styledDiv {
            css {
                margin(top = 16.px)
                alignSelf = Align.center
            }
            Typography("h4", story.name ?: "")
        }

        styledDiv {
            css {
                margin(top = 8.px)
                alignSelf = Align.center
            }
            Typography("h6", story.fullSlug ?: "")
        }

        styledDiv {
            css {
                margin(top = 24.px)
            }
            Typography("body1", story.slug ?: "")
        }
    }
}
