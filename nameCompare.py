# ========== ========== ========== ========== ==========
# Import libraries
# import sys
import os
import fnmatch
# import numpy as np
import pandas as pd
# import datetime

# ========== ========== ========== ========== ==========
# Global variables
borderIndent = '// '
borderIndentNew = '\n' + borderIndent
borderLine = borderIndent + '========== ' * 5
borderNewLine = '\n' + borderLine
columns_to_parse = [0, 1, 2, 3]
pattern = "CSA023*ITSM_Group_All_Queues*.xlsx"
reportNames = []


# ========== ========== ========== ========== ==========
#
def getFilenames(pattern):
    print(borderIndentNew +
          'Searching for files matching pattern')
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
# def main
def main():
    # Setup output condition
    noChangesDetected = True
    # Search local directory for files matching the pattern
    reportNames = getFilenames(pattern)
    # Terminate early if less than two files are found
    if len(reportNames) < 2:
        print(borderIndent + 'At least two files are required. Exiting...')
        exit(-1)
    # Pop the two newest filenames off the list
    filename0 = reportNames.pop()
    filename1 = reportNames.pop()
    # Read newest file into a dataframe and drop duplicates
    df0 = pd.read_excel(filename0,
                        sheet_name=0,
                        header=0,
                        usecols=columns_to_parse)
    df0 = df0.drop_duplicates(subset='Support Group ID', keep='first')
    # Read second newest file into a dataframe and drop duplicates
    df1 = pd.read_excel(filename1,
                        sheet_name=0,
                        header=0,
                        usecols=columns_to_parse)
    df1 = df1.drop_duplicates(subset='Support Group ID', keep='first')
    # Nested loops that perform the primary work
    my_outer_iter = df0.itertuples()
    for outer_tuple in my_outer_iter:
        my_inner_iter = df1.itertuples()
        for inner_tuple in my_inner_iter:
            if inner_tuple._4 == outer_tuple._4:
                if inner_tuple._2 != outer_tuple._2:
                    print(borderIndentNew, 'ORG change!',
                          inner_tuple._4, borderIndentNew,
                          '\t', inner_tuple._2, borderIndentNew,
                          '\t', outer_tuple._2)
                    noChangesDetected = False
                if inner_tuple._3 != outer_tuple._3:
                    print(borderIndentNew, 'GRP change!',
                          inner_tuple._4, borderIndentNew,
                          '\t', inner_tuple._3, borderIndentNew,
                          '\t', outer_tuple._3)
                    noChangesDetected = False

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
