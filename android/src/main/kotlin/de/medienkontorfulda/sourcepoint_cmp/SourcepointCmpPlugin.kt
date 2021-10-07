package de.medienkontorfulda.sourcepoint_cmp

import android.app.Activity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

/** SourcepointCmpPlugin */
class SourcepointCmpPlugin : FlutterPlugin, ActivityAware  {

    private var pluginBinding: FlutterPlugin.FlutterPluginBinding? = null


    // region FlutterPlugin
    final override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        pluginBinding = binding
    }

    final override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        pluginBinding = null
    }
    // endregion

    // region ActivityAware
    final override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        val activity = binding.activity as AppCompatActivity

        Log.d("FooBoo", "onAttachedToActivity ${pluginBinding!!.binaryMessenger}")
        val interstitialChannel = MethodChannel(pluginBinding!!.binaryMessenger, "sourcepoint_cmp")
        interstitialChannel.setMethodCallHandler(SourcepointCmp(activity, interstitialChannel, activity))

    }

    final override fun onDetachedFromActivity() {
    }

    final override fun onDetachedFromActivityForConfigChanges() =
        onDetachedFromActivity()

    final override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) =
        onAttachedToActivity(binding)
    // endregion
}
