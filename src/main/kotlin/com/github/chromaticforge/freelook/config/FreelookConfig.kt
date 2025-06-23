package com.github.chromaticforge.freelook.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Dropdown
import cc.polyfrost.oneconfig.config.annotations.DualOption
import cc.polyfrost.oneconfig.config.annotations.Info
import cc.polyfrost.oneconfig.config.annotations.KeyBind
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.core.OneKeyBind
import cc.polyfrost.oneconfig.config.data.InfoType
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.libs.universal.UKeyboard
import com.github.chromaticforge.freelook.Constants
import com.github.chromaticforge.freelook.hook.FreelookHook

object FreelookConfig : Config(
    Mod(
        Constants.NAME,
        ModType.UTIL_QOL,
        "/assets/${Constants.ID}/icon.svg"
    ), Constants.ID + ".json"
) {

    // None


    // None

    // Controls

    @KeyBind(name = "Freelook", subcategory = "Controls")
    var keyBind = OneKeyBind(UKeyboard.KEY_LMENU)

//    // TODO: Add "both" option that will toggle if pressed quickly and will hold if held
//    @Dropdown(name = "Freelook Mode", options = ["Hold", "Toggle"], subcategory = "Controls")
//    var mode = 0

    @DualOption(name = "Control Mode", left = "Hold", right = "Toggle", subcategory = "Controls")
    var mode = true

    // General

    @Dropdown(name = "Perspective", options = ["First", "Third", "Reverse"], subcategory = "General")
    var perspective = 1

    @DualOption(name = "Freelook Mode", left = "Freelook", right = "Snaplook", subcategory = "General")
    var freelook = false

    @Switch(name = "Invert Pitch (Up and Down)", subcategory = "General")
    var invertPitch = false

    @Switch(name = "Pitch Lock", subcategory = "General")
    var lockPitch = true

    @Switch(name = "Invert Yaw (Left and Right)", subcategory = "General")
    var invertYaw = false

    // General

    // Camera
    @Switch(name = "Smooth Change", subcategory = "Camera")
    var smoothCamera = true

    init {
        initialize()

        registerKeyBind(keyBind) { if (!mode) FreelookHook.togglePerspective() }

        hideIf("invertPitch") { freelook  }
        hideIf("lockPitch") { freelook  }
        hideIf("invertYaw") { freelook }

        addDependency("invertPitch", "pitch")
        addDependency("lockPitch", "pitch")
        addDependency("invertYaw", "yaw")
    }
}