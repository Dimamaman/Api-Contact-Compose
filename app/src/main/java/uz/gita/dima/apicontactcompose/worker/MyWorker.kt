package uz.gita.dima.apicontactcompose.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.onEach
import uz.gita.dima.apicontactcompose.domain.local.repository.AppRepository
import uz.gita.dima.apicontactcompose.domain.local.usecase.HomeUseCase
import uz.gita.dima.apicontactcompose.domain.network.repository.contact.ContactRepository
import uz.gita.dima.apicontactcompose.domain.network.usecase.contact.ContactUseCase
import javax.inject.Inject

@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

//    @Inject
//    lateinit var contactUseCase: ContactUseCase
//
//    @Inject
//    lateinit var homeUseCase: HomeUseCase



    @Inject
    lateinit var contactRepository: ContactRepository

    @Inject
    lateinit var repository: AppRepository

    override fun doWork(): Result {
        Log.d("TTT","onWork")
        repository.retrieveAllContacts().onEach {
            it.forEach { contactData ->
                contactRepository.addContact(contactData.toContactData())
            }
        }
        return Result.success()
    }
}