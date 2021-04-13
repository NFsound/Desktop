package presentation.sections.common

import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import presentation.styles.Colors
import tornadofx.SVGIcon

object Common {

    fun setMouseEnterBackground(icon: SVGIcon) {
        icon.setOnMouseEntered {
            icon.background = Background(
                BackgroundFill(
                    Colors.whiteColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        }
    }

    fun setMouseLeaveBackground(icon: SVGIcon) {
        icon.setOnMouseExited {
            icon.background = Background(
                BackgroundFill(
                    Colors.alternativeWhiteColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        }
    }


}