// This is an example. The actual package name might differ based on your project structure.
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.myapplication.databinding.FragmentProfileBinding

object FragmentProfileBinding {
    fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewBinding {
        return FragmentProfileBinding.inflate(inflater, container, attachToRoot)
    }
}
