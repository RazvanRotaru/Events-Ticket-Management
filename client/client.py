import requests, json
import sys, os

username = ''
userid = 0
server = 'http://172.16.1.3:8080/'
user = 'user/'
event = 'event/'
ticket = 'ticket/'
reservation = 'reservation/'
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
	print('\nChoose one of the following action:')
	print('1. Get all events in a location')
	print('2. Get all events in a location on a specific date')
	print('3. Book ticket')
	print('4. Buy ticket')
	print('5. EXIT')

	choice = input(' >> ')
	exec_action(choice)
	return

def getBl():
	print('\nWrite next information separated by comma and then press ENTER:\n'
		  '(If you want cancel type back)\n'
		  'Location')

	method = 'findByLocation/'

	inn = input(' >> ')

	if inn == 'back':
		exec_action('0')

	result = [x.strip() for x in inn.split(',')]

	if len(result) < 1:
		print('!!!!!!! Too few arguments')
		exec_action('1')

	location = result[0]
	
	print(server + event + method + location)
	response = requests.get(server + event + method + location)
	if response.status_code == 200:
		if response.text != '[]':
			print('-----> Event successfuly found: ')
			out = [x.strip() for x in response.text.split(',')]

			for i in range(0, 7):
				print(out[i])
		else:
			print("We could not found any event for you. We're sorry :( ")

		exec_action('0')
	else:
		print('!!!!!! Something went wrong. Please try again')
		exec_action('1')
	return

def getBlad():
	print('\nWrite next information separated by comma and then press ENTER:\n'
		  '(If you want cancel type back)\n'
		  'Location, Date')

	method = 'findByLocationAndDay/'

	inn = input(' >> ')

	if inn == 'back':
		exec_action('0')

	result = [x.strip() for x in inn.split(',')]

	if len(result) < 1:
		print('!!!!!!! Too few arguments')
		exec_action('2')

	location = result[0]
	day = result[1]

	response = requests.get(server + event + method + location + '/' + day)
	if response.status_code == 200:
		if response.text != '[]':
			print('-----> Event successfuly found: ')
			out = [x.strip() for x in response.text.split(',')]

			for i in range(0, 7):
				print(out[i])
		else:
			print("We could not found any route for you. We're sorry :( ")

		exec_action('0')
	else:
		print('!!!!!! Something went wrong. Please try again')
		exec_action('2')
	return


def book():
	print('\nPlease enter the events ID separated by comma: (If you want cancel type back)')

	stream = input(' >> ')

	if stream == 'back' or stream == '':
		exec_action('0')

	events = [x.strip() for x in stream.split(',')]

	method = 'add/'
	for event in events:
		link = server + reservation + method + username + '/' + event;
		response = requests.put(link, headers = headers)
		if response.status_code == 200:
			print('----> Event successfully booked')
			print('Please use this ticket id to buy your ticket: ' +  response.text)
			exec_action('0')
		else:
			print('!!!!!! Something went wrong. Please try again')
			exec_action('3')

	return

def buy():
	print('\nPlease enter the ticket ID you would like to buy and your credit card info separated by comma: (If you want cancel type back)')

	stream = input(' >> ')

	if stream == 'back':
		exec_action('0')


	args = [x.strip() for x in stream.split(',')]

	if len(args) < 2:
		print("\nYou need to provide us with your ticket ID and your credit card info separated by comma. \nPlease try again")
		exec_action('4')

	method = 'payTicket/' + args[0]

	response = requests.delete(server + ticket + method, headers = headers)

	if response.status_code == 200:
		print('----> Ticket successfully paid with next credit card: ' + args[1])
		print(' --------------------------------------------------------------- \n'
			  'Your ticket information is: \nticketID: ' + args[0] + '\nName: ' + username
			  + '\nevents: ' + response.text + '\n-------------------------------------------------------------- \n')
		exec_action('0')
	else:
		print('!!!!!! Something went wrong while paying your ticket')
		exec_action('3')
	return

def exit():
	sys.exit()

menu_actions = {
'0': main_menu,
'1': getBl,
'2': getBlad,
'3': book,
'4': buy,
'5': exit
}
if __name__ == '__main__':

	while username == '':
		print('Please let us know your name: ')
		username = input(" >> ")

	response = requests.get(server + user + 'getByName/' + username)
	if response.status_code == 200:
		print(response.text)
		if response.text == '':
			data = json.dumps({'name' : username})
			response = requests.put(server + user + 'add', data = data, headers = headers)

			if response.status_code == 200:
				main_menu()
		main_menu()
	else:
		print('Something went wrong. Please reboot app')

# 71FTR, BUC, AMT, 9, 21, 3, 100
# 32CD, BUC, LON, 3, 23, 4, 123
