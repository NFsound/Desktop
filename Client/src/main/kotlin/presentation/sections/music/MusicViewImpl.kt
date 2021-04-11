package presentation.sections.music

import javafx.collections.FXCollections.observableArrayList
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.ScrollPane
import javafx.scene.text.TextAlignment
import javafx.stage.FileChooser
import models.core.Network
import presentation.presenters.sections.MusicPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.sections.MusicViewStyles
import presentation.styles.sections.NewsViewStyles
import tornadofx.*

class MusicViewImpl() : View(), MusicView {

    override var sectionTitle = "Music"

    var fileLoaded = false

    //views
    lateinit var loadFileButton: Button
    lateinit var paramsComboBox: ComboBox<Network>
    lateinit var progressIndicator: ProgressIndicator

    override fun setPresenter(presenter: SectionPresenter) {
        musicPresenter = presenter as MusicPresenter
        musicPresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return musicPresenter
    }

    private lateinit var musicPresenter: MusicPresenter

    override val root: ScrollPane = scrollpane {
        addClass(NewsViewStyles.mainScrollViewStyle)
        isFitToWidth = true
        isFitToHeight = true
        vbox {
            addClass(NewsViewStyles.mainVBoxStyle)
            vbox {
                alignment = Pos.TOP_CENTER
                label("Future has come. Generate your music here and now") {
                    textAlignment = TextAlignment.CENTER
                    addClass(MusicViewStyles.topLabelStyle)
                }
            }
            hbox {
                vbox {

                    label("Easy as one-two-three:") {
                        addClass(MusicViewStyles.smallLabelStyle)
                    }

                    hbox {
                        label("1) Load your favourite composition") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        hbox {
                            alignment = Pos.CENTER

                            loadFileButton = button("Load file") {
                                addClass(MusicViewStyles.buttonMainDefaultStyle)
                                setOnMouseClicked {
                                    onLoadFileClicked()
                                }
                            }
                        }
                    }

                    hbox {
                        label("2) Choose your parameters") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        hbox {
                            alignment = Pos.CENTER
                            paramsComboBox = combobox {
                                title = "Choose generation network"
                                converter = NetworkConverter()
                                addClass(MusicViewStyles.comboBoxStyle)
                                setOnAction {
                                    musicPresenter.setCurrentNet(selectedItem)
                                }
                            }
                        }
                    }
                    hbox {
                        label("3) Enjoy generated music") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        hbox {
                            alignment = Pos.CENTER
                            progressIndicator = progressindicator {
                                addClass(MusicViewStyles.progressStyle)
                                isVisible = false
                            }
                        }
                    }
                }
            }


        }
    }

    override fun loadNetworks(networks: List<Network>) {
        paramsComboBox.items = observableArrayList(networks)
    }

    fun onLoadFileClicked() {
        if (!fileLoaded) {
            val extFilter = FileChooser.ExtensionFilter(
                "Music files (*.wav)",
                "*.wav"
            )
            try {
                musicPresenter.setCurrentFile(
                    chooseFile(
                        "Choose audio file",
                        arrayOf(extFilter),
                        mode = FileChooserMode.Single
                    )
                        .get(0)
                )
                loadFileButton.text = "Delete file"
                fileLoaded = true
            } catch (e: Exception) {
                //TODO workout
            }
        } else {
            musicPresenter.removeCurrentFile()
            loadFileButton.text = "Load file"
            fileLoaded = false
        }
    }

}