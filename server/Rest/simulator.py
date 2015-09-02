import os
import sys
import requests
import csv
import time

print('Nawras GEORGI - Stage Capgemini 2015')
print('Starting simulator')
print('')

file = open('D:/Users/ngeorgi/Desktop/sim.csv')
reader = csv.reader(file, delimiter=';')
for i, row in enumerate(reader):
    value = row[0]
    pause = row[1]
    if(i > 0):
      pause = float(pause)
      print('sending...')
      r = requests.put('http://localhost:8080/sncf/trains/' + value)
      time.sleep(pause)
file.close()
os.system("pause")