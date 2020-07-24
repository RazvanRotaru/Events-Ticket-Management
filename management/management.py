from flask import Flask
from flask import request
from flask import jsonify
from flaskext.mysql  import MySQL
import sys

# Creating MySQL instance
app = Flask(__name__)
mysql = MySQL()

# MySQL configurations
app.config['MYSQL_DATABASE_USER'] = 'root'
app.config['MYSQL_DATABASE_PASSWORD'] = 'q'
app.config['MYSQL_DATABASE_DB'] = 'eventsDb'
app.config['MYSQL_DATABASE_HOST'] = '172.16.1.2'

mysql.init_app(app)

headers = {'Content-type': 'application/json'}
menu_actions = {}

def exec_action(ch):
	choice = ch.lower()
	if ch == '':
		menu_actions['0']()
	else:
		try:
			menu_actions[choice]()
		except KeyError:
			print('Invalid selection, please try again.')
			menu_actions['0']()
	return

def main_menu():
	print('Choose one of the following action:')
	print('1. Add event')
	print('2. Cancel event')
	print('3. EXIT')

	choice = input(' >> ')
	exec_action(choice)
	return

def cancel():
	print('Please enter the event ID: (If you want cancel type back)')

	id = input(' >> ')

	if id == 'back':
		exec_action('0')

	command = 'delete from event where id=' + id

	try:
		conn = mysql.connect()
		cursor = conn.cursor()
		cursor.execute(command)
		conn.commit()
		
		cursor.close()
		conn.close()
	
		print('event successfully deleted')
		exec_action('0')

	except:
		print('!!!!!! Something went wrong. Please try again')
		exec_action('2')
	return

def add():
	print('Write next information separated by comma and then press ENTER:\n'
		  '(If you want cancel type back)\n'
		  'eventID, Location, Artist, Hour, Date, Duration, Number_of_seats\n')

	event = input(' >> ')

	if event == 'back':
		exec_action('0')

	result = [x.strip() for x in event.split(',')]
	print(result)
	if len(result) < 7:
		print('!!!!!!! Too few arguments')
		exec_action('1')


	command = "insert into event values(" + event + ")"

	print(command)
	try:
		conn = mysql.connect()
		cursor = conn.cursor()
		cursor.execute(command)
		conn.commit()
		cursor.close()
		conn.close()
	
		print('Event successfully added')
		exec_action('0')
		return

	except:
		print('!!!!!! Something went wrong. Please try again')
		exec_action('1')
	return

def exit():
	sys.exit()

menu_actions = {
'0': main_menu,
'2': cancel,
'1': add,
'3': exit
}
if __name__ == '__main__':
	
	main_menu()
