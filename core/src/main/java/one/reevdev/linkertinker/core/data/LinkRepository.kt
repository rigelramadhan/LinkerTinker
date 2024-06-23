package one.reevdev.linkertinker.core.data

import kotlinx.coroutines.flow.Flow
import one.reevdev.linkertinker.core.data.datasource.LinkFirestoreDataSource
import one.reevdev.linkertinker.core.data.model.LinkCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LinkRepository @Inject constructor(
    private val datastore: LinkFirestoreDataSource
) {

    fun getAllCategories(): Flow<Resource<List<LinkCategory>>> {
        return datastore.getAllCategories()
    }

    fun addCategory(category: LinkCategory): Flow<Resource<Boolean>> {
        return datastore.addCategory(category)
    }
}