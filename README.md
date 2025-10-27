📞 Contact Manager (Maven + PostgreSQL + JSON + Java)

A simple command-line Contact Manager built in Java, using Maven for project management, PostgreSQL for data storage, and Jackson for JSON serialization.
This project was created as a learning exercise to understand how Java applications connect to databases, perform CRUD operations, and work with JSON files.

🧭 Overview

The Contact Manager is a console-based application that allows you to:

➕ Add new contacts

🔍 View individual contacts

📋 View all contacts

✏️ Update contact information

❌ Delete contacts

📤 Export all contacts to a JSON file

📥 Import contacts from a JSON file

It’s designed to help beginners practice JDBC, Maven dependency management, JSON serialization/deserialization, and basic software architecture (Model–DAO–Service–UI layers).

⚙️ Features
Feature	Description
Add Contact	Save a new contact in the PostgreSQL database
View Contact	Retrieve a contact by its ID
View All Contacts	List all stored contacts
Update Contact	Modify an existing contact’s details
Delete Contact	Remove a contact from the database
Export to JSON	Serialize all contacts into a JSON file
Import from JSON	Read contacts from a JSON file and insert/update them in the DB
