package com.hexagonkt.rest

import com.hexagonkt.client.Client
import com.hexagonkt.server.Server
import com.hexagonkt.server.ServerPort
import com.hexagonkt.settings.SettingsManager.settings
import com.hexagonkt.store.MongoIdRepository
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

abstract class CrudTest <T : Any, K : Any> (
    type: KClass<T>, key: KProperty1<T, K>, serverAdapter: ServerPort) {

    val idCollection: MongoIdRepository<T, K> = MongoIdRepository(type, key)

    val server = Server(serverAdapter, settings)
    val client by lazy { Client("http://${server.bindAddress.hostAddress}:${server.runtimePort}") }

    fun startServer() {
        server.router.path(crud(idCollection))
        server.run()
    }

    fun stopServer() {
        server.stop()
    }

    fun crud_operations_behave_properly() {
//        val objects = createObjects ()
//        val changedObjects = objects.map { this.changeObject(it) }
//        val ids = objects.map { idCollection.getKey(it) }
//
//        client.post("/")
    }
}
