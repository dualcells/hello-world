# ========== ========== ========== ========== ==========
# Import libraries
import sys

import os
import fnmatch
import numpy as np
import pandas as pd
import datetime

# ========== ========== ========== ========== ==========
# Global variables
borderIndent = '// '
borderIndentNew = '\n' + borderIndent
borderLine = borderIndent + '========== ' * 5
borderNewLine = '\n' + borderLine

columns_to_parse = [1, 2, 3]

header_names = ['Support Company', 'Support Organization',
                'Support Group', 'Support Group ID',
                'Full Name', 'Email Address']

pattern = "CSA023*ITSM_Group_All_Queues*.xlsx"
reportNames = []
# ========== ========== ========== ========== ==========
#
if False:
    print(borderNewLine)
    print(borderIndent + 'Python version: ' + sys.version)
    print(borderIndent + 'Pandas version: ' + pd.__version__)
    print(borderIndent + 'NumPy version: ' + np.__version__)
    print(borderLine)


# ========== ========== ========== ========== ==========
#
def getFilenames(pattern):
    print(borderIndentNew +
          'Searching for files matching pattern [min: 2 required]')
    print(borderIndent + 'Pattern: ' + pattern)
    reportNames = []
    listOfFiles = os.listdir('.')
    for entry in listOfFiles:
        if fnmatch.fnmatch(entry, pattern):
            reportNames.append(entry)

    reportNames.sort()
    print(borderIndent + 'Files found: ' + str(len(reportNames)))
    return reportNames


# ========== ========== ========== ========== ==========
# def parse_excel_df(filename)
def parse_excel_df(filename):
    # Read Excel file to create dataFrame; limited by columns_to_parse
    print(borderIndentNew + 'Parsing ' + filename)
    df = pd.read_excel(filename, sheet_name=0, header=0,
                       usecols=columns_to_parse)

    # Drop duplicate rows; each member of a group exists on a separate row
    print(borderIndent + 'Removing duplicates...')
    df = df.drop_duplicates(subset='Support Group ID', keep='first')

    # Count how many rows are left
    print(borderIndent + 'Counting rows...')
    df_count = df['Support Group ID'].count()

    # Create Numpy array to create new index
    print(borderIndent + 'Creating new index...')
    new_index = np.arange(0, df_count, 1)

    # Insert column that will become the new index
    df['newIndex'] = pd.Series(new_index, index=df.index)

    # Set the index
    df.set_index('newIndex', inplace=True)

    # Return the formatted DataFrame
    return df


# ========== ========== ========== ========== ==========
# def compareLists
# replace with numpy.setdiff1d(df0, df1)
def compareLists(df0, df1):
    now = datetime.datetime.now()
    for x in range(0, int(df0.count(numeric_only=True))):
        group_id0 = df0.iloc[x, 2]
        for y in range(0, int(df1.count(numeric_only=True))):
            group_id1 = df1.iloc[y, 2]
            if group_id0 == group_id1:
                df0.iat[x, 3] = -1
                noChangesDetected = False
                break

    then = now
    now = datetime.datetime.now()
    print(borderIndent + 'Duration: ' + str(now - then))
    return df0


# ========== ========== ========== ========== ==========
# def printPositives
def printPositives(df):
    if int(df.max(numeric_only=True)) > 0:
        print(borderNewLine)
        print(borderIndent + df.columns[3] + '\n//')
        for x in range(0, int(df.count(numeric_only=True))):
            if df.iloc[x, 3] == 1:
                print(borderIndent + '-> ' + df.iloc[x, 0])
                print(borderIndent + '--> ' + df.iloc[x, 1])
                print(borderIndent + '---> ' + df.iloc[x, 2])

    return None


# ========== ========== ========== ========== ==========
# def main
def main():
    # Setup output condition
    noChangesDetected = True
    # Search local directory for files matching the pattern
    reportNames = getFilenames(pattern)

    if len(reportNames) < 2:
        print(borderIndent + 'At least two files are required. Exiting...')
        exit(-1)

    filename0 = reportNames.pop()
    filename1 = reportNames.pop()

    # Create DataFrame from Excel worksheet
    last0 = parse_excel_df(filename0)
    last0['isNew'] = pd.Series(1, index=last0.index)

    # Create DataFrame from Excel worksheet
    last1 = parse_excel_df(filename1)
    last1['isDisabled'] = pd.Series(1, index=last1.index)

    # If group exists in last0 only then isNew = 1
    print(borderIndentNew + 'Iterating through rows to find new groups...')
    last0 = compareLists(last0, last1)

    # If group exists in last1 only then isDisabled = 1
    print(borderIndentNew + 'Iterating through rows to find deleted groups...')
    last1 = compareLists(last1, last0)

    printPositives(last0)
    printPositives(last1)

    # Output notification
    print(borderNewLine)
    if (noChangesDetected):
        print(borderIndent, 'Result: No organization or group changes found.')
    else:
        print(borderIndent, 'Result: Organization or group changes found.')
    # Print files used for comparison
    print(borderIndentNew, 'Compared files:')
    print(borderIndent, '1.', filename0)
    print(borderIndent, '2.', filename1)
    # We're done. Exiting...
    exit(0)

    
if __name__ == '__main__':
    main()
