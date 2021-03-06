/**
*  Copyright (C) 2013-2014 Duncan DeVore. <https://github.com/ironfish/>
*/
package akka.persistence.mongo.journal

import akka.persistence.journal.JournalSpec
import akka.persistence.mongo.MongoCleanup
import akka.persistence.mongo.PortServer._

import com.typesafe.config.ConfigFactory

object CasbahJournalSpec {
  val genConfig = ConfigFactory.parseString(
    s"""
      |akka.persistence.journal.plugin = "casbah-journal"
      |akka.persistence.snapshot-store.plugin = "casbah-snapshot-store"
      |akka.persistence.publish-confirmations = on
      |akka.persistence.publish-plugin-commands = on
      |casbah-journal.mongo-journal-url = "mongodb://localhost:$freePort/store.messages"
      |casbah-journal.mongo-journal-write-concern = "acknowledged"
      |casbah-journal.mongo-journal-write-concern-timeout = 10000
      |casbah-snapshot-store.mongo-snapshot-url = "mongodb://localhost:$freePort/store.snapshots"
      |casbah-snapshot-store.mongo-snapshot-write-concern = "acknowledged"
      |casbah-snapshot-store.mongo-snapshot-write-concern-timeout = 10000
    """.stripMargin)
}

import CasbahJournalSpec._

class CasbahJournalSpec extends JournalSpec with MongoCleanup {
  lazy val config = genConfig
  override val actorSystem = system
}
