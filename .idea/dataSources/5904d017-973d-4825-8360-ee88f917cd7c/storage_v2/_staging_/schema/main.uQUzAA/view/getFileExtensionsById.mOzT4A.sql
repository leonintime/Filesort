CREATE VIEW getFileExtensionsById as
SELECT dest_fold_name, file_ext_ext
FROM file_moving,
     destination_folder,
     file_extensions,
     moving_files_folder
WHERE file_moving.dest_folder_id = destination_folder.dest_fold_id
  AND file_moving.mff_id = moving_files_folder.mff_id
  AND file_extensions.dest_folder_id = destination_folder.dest_fold_id
  AND file_moving.dest_folder_id = 3

