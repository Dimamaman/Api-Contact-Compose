package uz.gita.dima.apicontactcompose.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.onEach
import uz.gita.a5.contactappwithworkmanager.data.model.ContactData
import uz.gita.a5.contactappwithworkmanager.domain.repository.AppRepository
import uz.gita.a5.contactappwithworkmanager.domain.repository.ContactRepository
import javax.inject.Inject

@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : Worker(appContext, workerParams) {


    @Inject
    lateinit var appRepository: AppRepository
    @Inject
    lateinit var contactRepository: ContactRepository

    override fun doWork(): Result {

        Log.d("TTT", "${::appRepository.isInitialized}")
        Log.d("TTT", "${::contactRepository.isInitialized}")
        appRepository.getUnUploadedContacts().onEach {
            it.forEach { contactData ->
                contactRepository.addContact(contactData.toRequest())
                val newContact = ContactData(
                    id = contactData.id,
                    firstName = contactData.firstName,
                    lastName = contactData.lastName,
                    phone = contactData.phone,
                    uploaded = true
                )
                appRepository.update(newContact)
            }
        }
        return Result.success()
    }


}