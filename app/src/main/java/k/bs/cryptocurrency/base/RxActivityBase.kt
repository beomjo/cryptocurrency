package k.bs.cryptocurrency.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import k.bs.cryptocurrency.common.error.CanceledByUserException
import k.bs.cryptocurrency.common.util.rxresult.getObject
import k.bs.cryptocurrency.common.util.rxresult.putObject
import java.lang.reflect.Type


@SuppressLint("Registered")
open class RxActivityBase : AppCompatActivity() {
    inline fun <reified T> getInput(): T {
        return getInput(T::class.java)
    }

    fun <T> getInput(clazz: Type): T {
        return intent.getObject(clazz) as T
    }

    fun finishWithResponse(any: Any) {
        setResult(RESULT_OK, Intent().putObject(any))
        finish()
    }

    fun finishWithCancel() {
        finishWithException(CanceledByUserException())
    }

    fun finishWithException(t: Throwable) {
        setResult(Activity.RESULT_CANCELED, Intent().putObject(t))
        finish()
    }
}

